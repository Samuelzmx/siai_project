import requests
from bs4 import BeautifulSoup

def get_accession_number_for_apple_10k(year=2024):
    """
    Queries the SEC's search-index API for Apple's 10-K filings in the given year.
    Returns the accession number (adsh) of the first 10-K found, or None if no 10-K is found.
    """
    params = {
        "dateRange": "custom",
        "startdt": f"{year}-01-01",
        "enddt":   f"{year}-12-31",
        "forms":   "10-K",
        "ciks":    "0000320193",  # Apple's CIK
        "category": "custom"
    }
    headers = {
        "Accept": "application/json",
        "User-Agent": "MySECApp/1.0 (myemail@example.com)"  # Required by SEC
    }

    search_url = "https://efts.sec.gov/LATEST/search-index"
    resp = requests.get(search_url, params=params, headers=headers)
    resp.raise_for_status()

    data = resp.json()
    hits = data.get('hits', {}).get('hits', [])
    if not hits:
        return None

    # Return the accession number of the first 10-K filing found
    first_filing = hits[0].get('_source', {})
    return first_filing.get('adsh')  # e.g. "0000320193-24-000123"

def get_main_10k_url_from_index(cik_number, accession_number):
    """
    Given a numeric CIK (e.g., 320193) and an accession number (e.g. '0000320193-24-000123'),
    constructs the EDGAR "index" page URL and parses it to find the main 10-K document link.
    Returns the full URL to the 10-K document (which could be an iXBRL viewer link),
    or None if not found.
    """
    # Example index URL:
    #   https://www.sec.gov/Archives/edgar/data/320193/0000320193-24-000123/0000320193-24-000123-index.htm
    index_url = (
        f"https://www.sec.gov/Archives/edgar/data/{cik_number}/"
        f"{accession_number}/{accession_number}-index.htm"
    )

    headers = {
        "User-Agent": "MySECApp/1.0 (myemail@example.com)"
    }
    resp = requests.get(index_url, headers=headers)
    if resp.status_code != 200:
        print(f"Index page not found (status={resp.status_code}): {index_url}")
        return None

    soup = BeautifulSoup(resp.text, "html.parser")
    # The index page has a table with document links.
    # We'll look for a link ending in .htm, skipping the index page itself.
    possible_links = soup.select('table.tableFile a')
    for link in possible_links:
        href = link.get('href', '')
        if href.endswith('.htm') and 'index.htm' not in href:
            # Found a likely 10-K link
            doc_url = "https://www.sec.gov" + href
            return doc_url

    return None

def fix_ixbrl_url_if_needed(doc_url):
    """
    If doc_url is an iXBRL viewer link like:
      'https://www.sec.gov/ix?doc=/Archives/edgar/data/320193/0000320193-24-000123/aapl-20240928.htm'
    we remove 'ix?doc=' and any extra slashes so it becomes:
      'https://www.sec.gov/Archives/edgar/data/320193/0000320193-24-000123/aapl-20240928.htm'
    """
    if "ix?doc=" in doc_url:
        # remove 'ix?doc='
        doc_url = doc_url.replace("ix?doc=", "")
        # fix double slash if it appears
        doc_url = doc_url.replace("//Archives/", "/Archives/")
    return doc_url

def main():
    # Step 1: Try to get Apple’s 2024 10-K accession number
    adsh_2024 = get_accession_number_for_apple_10k(2024)
    if not adsh_2024:
        print("No Apple 10-K found for 2024. (Apple likely hasn't filed yet.)")
        return

    print(f"Found 2024 10-K: Accession Number = {adsh_2024}")

    # Apple's numeric CIK (no leading zeros) is 320193
    cik_number = 320193

    # Step 2: Parse the index page to find the main 10-K link
    doc_url = get_main_10k_url_from_index(cik_number, adsh_2024)
    if not doc_url:
        print("Could not find the main 10-K link in the index page.")
        return

    print("Initial 10-K URL from index page:", doc_url)

    # Step 3: If it's an iXBRL viewer link, convert to the raw HTML link
    raw_doc_url = fix_ixbrl_url_if_needed(doc_url)
    print("Final URL to download 10-K from:", raw_doc_url)

    # Step 4: Download the actual 10-K HTML
    headers = {"User-Agent": "MySECApp/1.0 (myemail@example.com)"}
    filing_resp = requests.get(raw_doc_url, headers=headers)
    if filing_resp.status_code != 200:
        print(f"Error fetching the 10-K HTML file: {filing_resp.status_code}")
        return

    # Step 5: Parse it with BeautifulSoup & show some text
    soup = BeautifulSoup(filing_resp.text, "html.parser")
    text_content = soup.get_text("\n", strip=True)

    print("\n===== START OF 10-K TEXT (first 500 chars) =====")
    print(text_content[:500])
    print("===== END =====\n")

    # Step 6: Save the entire HTML locally
    output_filename = "apple_10k_2024.html"
    with open(output_filename, "w", encoding="utf-8") as f:
        f.write(filing_resp.text)
    print(f"Saved the full 10-K HTML to '{output_filename}'")

if __name__ == "__main__":
    main()
