import React, { useState } from 'react';
import { getFiling } from './api/filingsApi';
import CompanySelector from './components/CompanySelector';
import YearSelector from './components/YearSelector';
import FilingTypeSelector from './components/FilingTypeSelector';
import SearchButton from './components/SearchButton';
import FilingResult from './components/FilingResult';

function App() {
  // State for form inputs
  const [company, setCompany] = useState('Apple');
  const [year, setYear] = useState('2025');
  const [filingType, setFilingType] = useState('10-K');
  // State for API response and statuses
  const [filingContent, setFilingContent] = useState(null);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);
  const [hasSearched, setHasSearched] = useState(false);

  // Handle the search button click
  const handleSearch = async () => {
    setHasSearched(true);        // Mark that a search was initiated
    setLoading(true);            // Show loading state
    setError(null);              // Reset any previous errors
    setFilingContent(null);      // Clear previous content
    try {
      // Fetch the filing from the backend API
      const data = await getFiling(company, year, filingType);
      if (data) {
        setFilingContent(data);  // Filing found – store its content
      } else {
        setFilingContent(null);  // No data returned – will indicate "Not Found"
      }
    } catch (err) {
      // Handle network or other errors
      setError('Failed to fetch data.');  
    } finally {
      setLoading(false);         // Done loading (either success or failure)
    }
  };

  return (
    <div>
      {/* Selection Inputs */}
      <CompanySelector value={company} onChange={setCompany} />
      <YearSelector value={year} onChange={setYear} />
      <FilingTypeSelector value={filingType} onChange={setFilingType} />
      <SearchButton onClick={handleSearch} disabled={loading} />

      {/* Result Display */}
      {loading && <p>Loading...</p>}
      {!loading && hasSearched && (
        <FilingResult content={filingContent} error={error} />
      )}
    </div>
  );
}

export default App;
