import React, { useState } from 'react';
import { createCompany } from './api';

function CompanyForm() {
  const [name, setName] = useState('');
  const [ticker, setTicker] = useState('');
  const [industry, setIndustry] = useState('');
  const [sector, setSector] = useState('');

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const newCo = { name, ticker, industry, sector };
      const created = await createCompany(newCo);
      console.log('Created company:', created);
      // Clear the form
      setName('');
      setTicker('');
      setIndustry('');
      setSector('');
    } catch (error) {
      console.error('Error creating company:', error);
    }
  };

  return (
    <div>
      <h2>Create New Company</h2>
      <form onSubmit={handleSubmit}>
        <div>
          <label>Name: </label>
          <input 
            value={name}
            onChange={(e) => setName(e.target.value)}
            required
          />
        </div>
        <div>
          <label>Ticker: </label>
          <input 
            value={ticker}
            onChange={(e) => setTicker(e.target.value)}
            required
          />
        </div>
        <div>
          <label>Industry: </label>
          <input
            value={industry}
            onChange={(e) => setIndustry(e.target.value)}
          />
        </div>
        <div>
          <label>Sector: </label>
          <input 
            value={sector}
            onChange={(e) => setSector(e.target.value)}
          />
        </div>
        <button type="submit">Create</button>
      </form>
    </div>
  );
}

export default CompanyForm;
