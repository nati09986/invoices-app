import React, { useEffect, useState } from 'react';
import InvoiceStatusChart from '../components/Charts/InvoiceStatusChart';
import MonthlyInvoiceChart from '../components/Charts/MonthlyInvoiceChart';
import OverdueTrendChart from '../components/Charts/OverdueTrendChart';
import CustomerAnalysisChart from '../components/Charts/CustomerAnalysisChart';
import InvoiceFilter from '../components/Filters/InvoiceFilter';
import { fetchInvoices } from '../services/api';
import { InvoiceData } from '../types/invoiceTypes';
import { FilterCriteria } from '../types/filterTypes';

const Dashboard: React.FC = () => {
    const [filters, setFilters] = useState<FilterCriteria>({
        startDate: '',
        endDate: '',
        status: '',
        customer: '',
    });

    const [invoicesData, setData] = useState<InvoiceData[]>([]);

    useEffect(() => {
        const fetchData = async () => {
            try {
                const response = await fetchInvoices(filters);
                setData(response);
            } catch (error) {
                console.error('Failed to fetch invoices:', error);
            }
        };

        fetchData();
    }, [filters]);

    return (
        <div>
            <h1>Dashboard</h1>
            <InvoiceFilter onFilterChange={setFilters} />
            <div className="charts">
                <InvoiceStatusChart data={invoicesData.statusData} />
                <MonthlyInvoiceChart data={invoicesData.monthlyData} />
                <OverdueTrendChart data={invoicesData.overdueData} />
                <CustomerAnalysisChart data={invoicesData.customerData} />
            </div>
        </div>
    );
};

export default Dashboard;
