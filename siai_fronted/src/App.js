import React from 'react';
import CompanyList from './CompanyList';
import CompanyForm from './CompanyForm';

function App() {
  return (
    <div style={{ margin: '20px' }}>
      <h1>SIAI Database Frontend</h1>
      <CompanyForm />
      <hr />
      <CompanyList />
    </div>
  );
}

export default App;
