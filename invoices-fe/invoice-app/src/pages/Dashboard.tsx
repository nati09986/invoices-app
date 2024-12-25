import React, { useState, useEffect } from 'react';
import { Box, Typography } from '@mui/material';
import ApiService from '../services/ApiService';
import { Invoice } from '../types/invoice';
import { DateRange } from '../types/dateRange';
import { ChartType } from '../types/chartType';
import InvoiceFilters from '../components/InvoiceFilter';
import TotalInvoicesChart from '../components/TotalInvoicesChart';
import OverdueTrendChart from '../components/OverdueTrendChart';

const Dashboard: React.FC = () => {
    const apiService = new ApiService('http://localhost:8080/api');
    const [data, setData] = useState<Invoice[]>([]);
    const [dateRange, setDateRange] = useState<DateRange>({
        startDate: '2024-09-01',
        endDate: new Date().toISOString().split('T')[0]
    });
    const [status, setStatus] = useState<string>('');
    const [customer, setCustomer] = useState<string>('');
    const [chartType, setChartType] = useState<ChartType>({
        totalInvoices: 'bar',
        overdueTrend: 'line',
        monthlyTotals: 'bar',
        customerAnalysis: 'bar'
    });

    useEffect(() => {
        fetchData();
    }, [dateRange, status, customer]);

    const fetchData = async (): Promise<void> => {
        try {
            const invoices = await apiService.fetchInvoices(
                dateRange.startDate.trim().length === 0 ? undefined : dateRange.startDate,
                dateRange.endDate.trim().length === 0 ? undefined : dateRange.endDate,
                customer.trim().length === 0 ? undefined : customer,
                status.trim().length === 0 ? undefined : status
            );
            setData(invoices);
        } catch (err) {
            console.warn('Failed to fetch data.');
        }
    };

    const handleChartTypeChange = (chartKey: keyof ChartType, value: string): void => {
        setChartType({ ...chartType, [chartKey]: value as any });
    };

    return (
        <Box>
            <Typography variant="h4" gutterBottom>
                Invoice Dashboard
            </Typography>
            <InvoiceFilters
                dateRange={dateRange}
                status={status}
                customer={customer}
                onDateRangeChange={setDateRange}
                onStatusChange={setStatus}
                onCustomerChange={setCustomer}
            />
            <Box display="grid" gridTemplateColumns="repeat(auto-fill, minmax(400px, 1fr))" gap={5} margin="0 20px">
                <TotalInvoicesChart
                    data={data}
                    chartType={chartType.totalInvoices}
                    onChartTypeChange={(value) => handleChartTypeChange('totalInvoices', value)}
                />
                <OverdueTrendChart
                    data={data}
                    chartType={chartType.overdueTrend}
                    onChartTypeChange={(value) => handleChartTypeChange('overdueTrend', value)}
                />
            </Box>
        </Box>
    );
};

export default Dashboard;