import React from 'react';

function SearchButton({ onClick, disabled }) {
  return (
    <div>
      <button onClick={onClick} disabled={disabled}>
        Search
      </button>
    </div>
  );
}

export default SearchButton;
