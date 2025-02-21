import React from 'react';

function CompanySelector({ value, onChange }) {
  return (
    <div>
      <label htmlFor="company-select">Company: </label>
      <select
        id="company-select"
        value={value}
        onChange={(e) => onChange(e.target.value)}
      >
        <option value="Apple Inc.">Apple Inc.</option>
        <option value="Tesla Inc.">Tesla Inc.</option>
      </select>
    </div>
  );
}

export default CompanySelector;
