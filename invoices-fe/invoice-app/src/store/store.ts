import { configureStore } from '@reduxjs/toolkit';
// import invoicesReducer from './invoicesSlice';

export const store = configureStore({
  reducer: {
    // invoices: invoicesReducer,
  },
});

export default store;
// export type RootState = ReturnType<typeof store.getState>;
// export type AppDispatch = typeof store.dispatch;