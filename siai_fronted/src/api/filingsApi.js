// src/api/filingsApi.js

export async function getFiling(company, year, type) {
  // 1) The backend expects a full 'date' param, e.g. "2025-01-31"
  //    We'll build that from the 'year' the user selected:
  const fullDate = `${year}-01-31`;

  // 2) Adjust the URL so it points to your Spring Boot app on localhost:8080
  //    with the param names: company, filingType, date
  const queryUrl = `http://localhost:8080/api/secfiling/search`
    + `?company=${encodeURIComponent(company)}`
    + `&filingType=${encodeURIComponent(type)}`
    + `&date=${encodeURIComponent(fullDate)}`;

  // 3) Make a fetch call to the above URL
  const response = await fetch(queryUrl);

  // 4) Handle errors:
  if (!response.ok) {
    if (response.status === 404) {
      // No filing found
      return null;
    }
    // Some other error (500, 400, etc.)
    throw new Error(`Server error: ${response.status}`);
  }

  // 5) Parse JSON and return
  const data = await response.json();
  return data;
}
