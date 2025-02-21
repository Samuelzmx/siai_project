import React from 'react';

function FilingTypeSelector({ value, onChange }) {
  return (
    <div>
      <label htmlFor="type-select">Filing Type: </label>
      <select 
        id="type-select" 
        value={value} 
        onChange={(e) => onChange(e.target.value)}
      >
        <option value="10-K">10-K</option>
        {/* Additional filing types (e.g., 10-Q) can be added here */}
      </select>
    </div>
  );
}

export default FilingTypeSelector;
