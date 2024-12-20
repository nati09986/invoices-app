import { createAsyncThunk, createSlice } from '@reduxjs/toolkit';
import { getInvoices } from '../services/invoicesService';

export const fetchInvoices = createAsyncThunk('invoices/fetch', async (params: Record<string, any>) => {
  return await getInvoices(params);
});

const invoicesSlice = createSlice({
  name: 'invoices',
  initialState: {
    data: [],
    status: 'idle',
    error: null,
  },
  reducers: {},
  extraReducers: (builder) => {
    builder
      .addCase(fetchInvoices.pending, (state) => {
        state.status = 'loading';
      })
      .addCase(fetchInvoices.fulfilled, (state, action) => {
        state.status = 'succeeded';
        state.data = action.payload;
      })
      .addCase(fetchInvoices.rejected, (state, action) => {
        state.status = 'failed';
        state.error = action.error.message;
      });
  },
});

export default invoicesSlice.reducer;
