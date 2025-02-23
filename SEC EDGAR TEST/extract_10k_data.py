import os
import requests
import csv
from datetime import datetime

# SEC API Base URL
SEC_API_BASE = "https://www.sec.gov"

# Required Headers
HEADERS = {
    "User-Agent": "jackbian0903@gmail.com"
}

# Directory for storing filings
SAVE_DIR = "10-K_Filings"
os.makedirs(SAVE_DIR, exist_ok=True)

# Metadata file
METADATA_FILE = os.path.join(SAVE_DIR, "10-K_metadata.csv")

def get_latest_10k(cik: str, ticker: str):
    """
    Fetches the latest 10-K filing for a given company's CIK.
    :param cik: Company CIK
    :param ticker: Company ticker symbol
    :return: Filing URL if available
    """
    filings_url = f"https://data.sec.gov/submissions/CIK{cik}.json"

    response = requests.get(filings_url, headers=HEADERS)
    if response.status_code != 200:
        print(f"❌ Error fetching data for {ticker}: {response.status_code}")
        return None

    data = response.json()
    filings = data.get("filings", {}).get("recent", {})

    # Find the latest 10-K
    for i, form in enumerate(filings.get("form", [])):
        if form == "10-K":
            accession_number = filings["accessionNumber"][i].replace("-", "")
            primary_doc = filings["primaryDocument"][i]  
            filing_url = f"{SEC_API_BASE}/Archives/edgar/data/{cik}/{accession_number}/{primary_doc}"

            # Extract Filing Year
            filing_year = datetime.now().year  # Get the current year

            return {
                "company_cik": cik,
                "ticker": ticker,
                "filing_year": filing_year,
                "filing_url": filing_url
            }

    print(f"❌ No 10-K filing found for {ticker} ({cik}).")
    return None

def save_10k(filing_data):
    """
    Downloads and saves the 10-K filing document.
    """
    if not filing_data:
        return

    ticker = filing_data["ticker"]
    filing_year = filing_data["filing_year"]
    filing_url = filing_data["filing_url"]

    response = requests.get(filing_url, headers=HEADERS)
    if response.status_code != 200:
        print(f"❌ Error fetching 10-K for {ticker}: {response.status_code}")
        return

    # Define file path for saving
    filename = f"{ticker}_{filing_year}_10-K.html"
    filepath = os.path.join(SAVE_DIR, filename)

    # Save the document
    with open(filepath, "wb") as f:
        f.write(response.content)
    
    print(f"✅ 10-K saved as {filepath}")

    # Store metadata
    save_metadata(filing_data, filepath)

def save_metadata(filing_data, filepath):
    """
    Stores metadata in a CSV file to keep track of all 10-K filings.
    """
    csv_file = METADATA_FILE
    file_exists = os.path.isfile(csv_file)

    # Read existing metadata to prevent duplicates
    existing_entries = set()
    if file_exists:
        with open(csv_file, "r", newline="") as f:
            reader = csv.reader(f)
            next(reader, None)  # Skip header
            for row in reader:
                existing_entries.add((row[0], row[2]))  # (Ticker, Filing Year)

    # Append only if not already stored
    if (filing_data["ticker"], str(filing_data["filing_year"])) not in existing_entries:
        with open(csv_file, "a", newline="") as f:
            writer = csv.writer(f)
            if not file_exists:
                writer.writerow(["Ticker", "CIK", "Filing Year", "File Path", "URL"])
            writer.writerow([
                filing_data["ticker"],
                filing_data["company_cik"],
                filing_data["filing_year"],
                filepath,
                filing_data["filing_url"]
            ])
        print("✅ Metadata saved.")
    else:
        print(f"⚠️ Metadata already exists for {filing_data['ticker']} ({filing_data['filing_year']}). Skipping.")

# Example: Fetch and save the latest 10-K for multiple companies
companies = {
    "AAPL": "0000320193",
    "MSFT": "0000789019",
    "TSLA": "0001318605",
    "GOOGL": "0001652044",
    "AMZN": "0001018724",
    "NFLX": "0001065280"
}

for ticker, cik in companies.items():
    filing_data = get_latest_10k(cik, ticker)
    if filing_data:
        save_10k(filing_data)
