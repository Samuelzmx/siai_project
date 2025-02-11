import axios from 'axios';

const api = axios.create({
  baseURL: 'http://localhost:8080/api',
});

// GET all
export const getAllCompanies = async () => {
  try {
    const response = await api.get('/company');
    return response.data;
  } catch (error) {
    console.error('Error fetching all companies:', error);
    throw error; // re-throw so calling code knows there was an error
  }
};

// POST create
export const createCompany = async (company) => {
  try {
    const response = await api.post('/company', company);
    return response.data;
  } catch (error) {
    console.error('Error creating company:', error);
    throw error;
  }
};

// DELETE
export const deleteCompany = async (id) => {
  try {
    await api.delete(`/company/${id}`);
  } catch (error) {
    console.error(`Error deleting company with id=${id}:`, error);
    throw error;
  }
};
