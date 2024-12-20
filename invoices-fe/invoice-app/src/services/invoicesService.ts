import api from './api';

export const getInvoices = async (params: Record<string, any>) => {
  const response = await api.get('/invoices', { params });
  return response.data;
};
