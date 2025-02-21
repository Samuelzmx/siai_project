import React from 'react';

function YearSelector({ value, onChange }) {
  return (
    <div>
      <label htmlFor="year-select">Year: </label>
      <select 
        id="year-select" 
        value={value} 
        onChange={(e) => onChange(e.target.value)}
      >
        <option value="2025-01-31">2025-01-31</option>
        {/* Additional years can be added here */}
      </select>
    </div>
  );
}

export default YearSelector;
