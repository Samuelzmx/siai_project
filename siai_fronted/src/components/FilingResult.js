import React from 'react';

function FilingResult({ content, error }) {
  if (error) {
    return <div style={{ color: 'red' }}>Error: {error}</div>;
  }
  if (!content) {
    return <div>No filing found.</div>;
  }

  // We have a SecFiling object
  return (
    <div style={{ border: '1px solid #ccc', padding: '8px', marginTop: '12px' }}>
      <h3>Filing Found</h3>
      <p><strong>Company:</strong> {content.company?.name}</p>
      <p><strong>Type:</strong> {content.filingType}</p>
      <p><strong>Date:</strong> {content.filingDate}</p>
      <p><strong>Content:</strong> {content.filingContent}</p>
      {/* Render anything else you need: e.g. url */}
    </div>
  );
}

export default FilingResult;
