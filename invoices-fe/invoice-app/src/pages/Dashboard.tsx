import React, { useEffect } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { fetchInvoices } from '../store/invoicesSlice';
import InvoiceStatusChart from '../components/charts/InvoiceStatusChart';
import OverdueTrendChart from '../components/charts/OverdueTrendChart';
import MonthlyInvoiceChart from '../components/charts/MonthlyInvoiceChart';
import CustomerAnalysisChart from '../components/charts/CustomerAnalysisChart';
import DateRangePicker from '../components/filters/DateRangePicker';
import StatusFilter from '../components/filters/StatusFilter';
import CustomerFilter from '../components/filters/CustomerFilter';

const Dashboard: React.FC = () => {
  const dispatch = useDispatch();

  useEffect(() => {
    dispatch(fetchInvoices());
  }, [dispatch]);

  return (
    <div>
      <h1>Invoice Dashboard</h1>
      <div className="filters">
        <DateRangePicker />
        <StatusFilter />
        <CustomerFilter />
      </div>
      <div className="charts">
        <InvoiceStatusChart />
        <OverdueTrendChart />
        <MonthlyInvoiceChart />
        <CustomerAnalysisChart />
      </div>
    </div>
  );
};

export default Dashboard;
