import os
import requests
import csv
import time
from datetime import datetime

# SEC API Base URL
SEC_API_BASE = "https://www.sec.gov"

# Required Headers
HEADERS = {
    "User-Agent": "your-email@example.com"
}

# Directory for storing filings
SAVE_DIR = "SEC_Filings"
os.makedirs(SAVE_DIR, exist_ok=True)

# Metadata file path
METADATA_FILE = os.path.join(SAVE_DIR, "filings_metadata.csv")


def get_cik_from_ticker(ticker: str):
    """
    Retrieves the CIK number from the company ticker symbol.
    :param ticker: Stock ticker symbol (e.g., AAPL, MSFT)
    :return: Corresponding CIK number as a string or None if not found
    """
    ticker = ticker.upper()
    cik_url = "https://www.sec.gov/files/company_tickers.json"

    try:
        response = requests.get(cik_url, headers=HEADERS, timeout=10)
        response.raise_for_status()
        cik_data = response.json()
    except requests.exceptions.RequestException as e:
        print(f"❌ Error fetching CIK data: {e}")
        return None

    for entry in cik_data.values():
        if entry["ticker"] == ticker:
            cik = str(entry["cik_str"]).zfill(10)  # Format CIK as a 10-digit string
            print(f"✅ Found CIK for {ticker}: {cik}")
            return cik

    print(f"⚠️ No CIK found for ticker {ticker}")
    return None


def get_filing(cik: str, ticker: str, year: int, form_type: str):
    """
    Fetches the specified filing (10-K or 10-Q) for a given company's CIK and year.
    """
    filings_url = f"https://data.sec.gov/submissions/CIK{cik}.json"

    try:
        response = requests.get(filings_url, headers=HEADERS, timeout=10)
        response.raise_for_status()
    except requests.exceptions.RequestException as e:
        print(f"❌ Request error for {ticker}: {e}")
        return None

    data = response.json()
    filings = data.get("filings", {}).get("recent", {})

    # Look for the correct filing
    for i, form in enumerate(filings.get("form", [])):
        filing_date = filings.get("filingDate", [])[i]
        filing_year = int(filing_date.split("-")[0]) if filing_date else None

        if form == form_type and filing_year == year:
            accession_number = filings["accessionNumber"][i].replace("-", "")
            primary_doc = filings["primaryDocument"][i]
            filing_url = f"{SEC_API_BASE}/Archives/edgar/data/{cik}/{accession_number}/{primary_doc}"

            return {
                "company_cik": cik,
                "ticker": ticker,
                "filing_year": filing_year,
                "form_type": form_type,
                "filing_url": filing_url
            }

    print(f"⚠️ No {form_type} filing found for {ticker} ({cik}) in {year}.")
    return None


def save_filing(filing_data):
    """
    Downloads and saves the filing document.
    """
    if not filing_data:
        return

    ticker = filing_data["ticker"]
    filing_year = filing_data["filing_year"]
    form_type = filing_data["form_type"]
    filing_url = filing_data["filing_url"]

    try:
        response = requests.get(filing_url, headers=HEADERS, timeout=15)
        response.raise_for_status()
    except requests.exceptions.RequestException as e:
        print(f"❌ Error fetching {form_type} for {ticker}: {e}")
        return

    # Define file path for saving
    filename = f"{ticker}_{filing_year}_{form_type}.html"
    filepath = os.path.join(SAVE_DIR, filename)

    # Save the document
    with open(filepath, "wb") as f:
        f.write(response.content)

    print(f"✅ {form_type} saved as {filepath}")

    # Store metadata
    save_metadata(filing_data, filepath)


def save_metadata(filing_data, filepath):
    """
    Stores metadata in a CSV file to keep track of all filings.
    """
    csv_file = METADATA_FILE
    file_exists = os.path.isfile(csv_file)

    # Read existing metadata quickly to avoid duplication
    existing_entries = set()
    if file_exists:
        with open(csv_file, "r", newline="") as f:
            reader = csv.reader(f)
            next(reader, None)  # Skip header
            for row in reader:
                existing_entries.add((row[0], row[2], row[3]))  # (Ticker, Filing Year, Form Type)

    # Append new data only if it's not already in the file
    if (filing_data["ticker"], str(filing_data["filing_year"]), filing_data["form_type"]) not in existing_entries:
        with open(csv_file, "a", newline="") as f:
            writer = csv.writer(f)
            if not file_exists:
                writer.writerow(["Ticker", "CIK", "Filing Year", "Form Type", "File Path", "URL"])
            writer.writerow([
                filing_data["ticker"],
                filing_data["company_cik"],
                filing_data["filing_year"],
                filing_data["form_type"],
                filepath,
                filing_data["filing_url"]
            ])
    else:
        print(f"⚠️ Metadata already exists for {filing_data['ticker']} ({filing_data['filing_year']} - {filing_data['form_type']}). Skipping.")


def main():
    """
    Main function to fetch SEC filings for any company.
    """
    while True:
        try:
            company_input = input("Enter the company ticker or CIK: ").strip().upper()
            year = int(input("Enter the filing year (e.g., 2023): "))
            form_type = input("Enter the form type (10-K or 10-Q): ").strip().upper()

            if form_type not in ["10-K", "10-Q"]:
                print("⚠️ Invalid form type. Please enter '10-K' or '10-Q'.")
                continue

            # Determine CIK
            cik = company_input if company_input.isdigit() else get_cik_from_ticker(company_input)

            if not cik:
                print("⚠️ Unable to determine CIK. Please enter a valid ticker or CIK.")
                continue

            break  # Exit loop when valid input is received
        except ValueError:
            print("⚠️ Please enter a valid year.")

    # Fetch and save the filing
    filing_data = get_filing(cik, company_input, year, form_type)
    if filing_data:
        save_filing(filing_data)

        # Avoid hitting SEC rate limits
        time.sleep(1)


if __name__ == "__main__":
    main()
