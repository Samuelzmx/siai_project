import React, { useEffect, useState } from 'react';
import { getAllCompanies, deleteCompany } from './api';

function CompanyList() {
  const [companies, setCompanies] = useState([]);

  useEffect(() => {
    fetchCompanies();
  }, []);

  const fetchCompanies = async () => {
    try {
      const data = await getAllCompanies();
      setCompanies(data);
    } catch (error) {
      console.error('Failed to fetch companies:', error);
    }
  };

  const handleDelete = async (id) => {
    try {
      await deleteCompany(id);
      fetchCompanies();
    } catch (error) {
      console.error('Error deleting company:', error);
    }
  };

  return (
    <div>
      <h2>Company List</h2>
      <table border="1" cellPadding="5" style={{ borderCollapse: 'collapse' }}>
        <thead>
          <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Ticker</th>
            <th>Industry</th>
            <th>Sector</th>
            <th>Action</th>
          </tr>
        </thead>
        <tbody>
          {companies.map((co) => (
            <tr key={co.companyId}>
              <td>{co.companyId}</td>
              <td>{co.name}</td>
              <td>{co.ticker}</td>
              <td>{co.industry}</td>
              <td>{co.sector}</td>
              <td>
                <button onClick={() => handleDelete(co.companyId)}>Delete</button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

export default CompanyList;
