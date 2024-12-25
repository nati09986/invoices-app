import React, { useState, useEffect, JSX } from 'react';
import { Box, Typography, CircularProgress, MenuItem, TextField, ToggleButton, ToggleButtonGroup, Button } from '@mui/material';
import { PieChart, Pie, Cell, BarChart, Bar, XAxis, YAxis, Tooltip, Legend, ResponsiveContainer, LineChart, Line, AreaChart, Area } from 'recharts';
import ApiService from '../services/ApiService';
import { Invoice } from '../types/invoice';
import { DateRange } from '../types/dateRange';
import { ChartType } from '../types/chartType';

const Dashboard: React.FC = () => {
    const apiService: ApiService = new ApiService('http://localhost:8080/api');
    const [data, setData] = useState<Invoice[]>([]);
    const [dateRange, setDateRange] = useState<DateRange>({ startDate: '2024-09-01', endDate: new Date().toISOString().split('T')[0] });
    const [status, setStatus] = useState<string>('');
    const [customer, setCustomer] = useState<string>('');
    const [chartType, setChartType] = useState<ChartType>({ totalInvoices: 'bar', overdueTrend: 'line', monthlyTotals: 'bar', customerAnalysis: 'bar' });

    useEffect(() => {
        fetchData();
    }, [dateRange, status, customer]);

    const fetchData = async (): Promise<void> => {
        try {
            const invoices: Invoice[] = await apiService.fetchInvoices(
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

    const handleChartTypeChange = (chartKey: keyof ChartType, value: string | null): void => {
        if (value) {
            setChartType({ ...chartType, [chartKey]: value as 'bar' | 'pie' | 'line' | 'area' });
        }
    };

    const renderTotalInvoicesChart = (): JSX.Element => {
        const totals = data.reduce<Record<string, number>>((acc, invoice) => {
            acc[invoice.invoiceStatus] = (acc[invoice.invoiceStatus] || 0) + invoice.invoiceCost;
            return acc;
        }, {});

        const chartData = Object.entries(totals).map(([status, total]) => ({ status, total }));

        return chartType.totalInvoices === 'bar' ? (
            <BarChart data={chartData}>
                <XAxis dataKey="status" />
                <YAxis />
                <Tooltip />
                <Legend />
                <Bar dataKey="total" fill="#8884d8" />
            </BarChart>
        ) : (
            <PieChart>
                <Pie data={chartData} dataKey="total" nameKey="status" cx="50%" cy="50%" outerRadius={120} label={(entry) => `${entry.status}: ${entry.total}`}>
                    {chartData.map((entry, index) => (
                        <Cell key={`cell-${index}`} fill={['#8884d8', '#82ca9d', '#ffc658'][index % 3]} />
                    ))}
                </Pie>
            </PieChart>
        );
    };

    const renderOverdueTrendChart = (): JSX.Element => {
        const overdueData = data
            .filter(invoice => new Date(invoice.invoiceDueDate) < new Date())
            .reduce<Record<string, number>>((acc, invoice) => {
                const date = invoice.invoiceDueDate.split('T')[0];
                acc[date] = (acc[date] || 0) + invoice.invoiceCost;
                return acc;
            }, {});

        const chartData = Object.entries(overdueData).map(([date, overdueAmount]) => ({ date, overdueAmount }));

        return chartType.overdueTrend === 'line' ? (
            <LineChart data={chartData}>
                <XAxis dataKey="date" />
                <YAxis />
                <Tooltip />
                <Legend />
                <Line type="monotone" dataKey="overdueAmount" stroke="#8884d8" />
            </LineChart>
        ) : (
            <AreaChart data={chartData}>
                <XAxis dataKey="date" />
                <YAxis />
                <Tooltip />
                <Legend />
                <Area type="monotone" dataKey="overdueAmount" stroke="#8884d8" fill="#8884d8" />
            </AreaChart>
        );
    };

    const renderFilters = (): JSX.Element => (
        <Box display="flex" gap={2} mb={4}>
            <TextField
                label="Start Date"
                type="date"
                value={dateRange.startDate}
                onChange={(e) => setDateRange({ ...dateRange, startDate: e.target.value })}
                InputLabelProps={{ shrink: true }}
            />
            <TextField
                label="End Date"
                type="date"
                value={dateRange.endDate}
                onChange={(e) => setDateRange({ ...dateRange, endDate: e.target.value })}
                InputLabelProps={{ shrink: true }}
            />
            <TextField
                label="Invoice Status"
                select
                value={status}
                onChange={(e) => setStatus(e.target.value)}
                style={{ width: '15vw' }}
            >
                <MenuItem value=''>Clear</MenuItem>
                <MenuItem value='CONFIRMED'>Confirmed</MenuItem>
                <MenuItem value='CANCELLED'>Cancelled</MenuItem>
            </TextField>
            <TextField
                label="Customer"
                value={customer}
                onChange={(e) => setCustomer(e.target.value)}
            />
        </Box>
    );

    return (
        <Box>
            <Typography variant="h4" gutterBottom>
                Invoice Dashboard
            </Typography>
            {renderFilters()}
            <Box display="grid" gridTemplateColumns="repeat(auto-fill, minmax(400px, 1fr))" gap={5} margin={"0 20px"}>
                <Box>
                    <Typography variant="h6">Total Invoice Amounts by Status</Typography>
                    <ToggleButtonGroup
                        value={chartType.totalInvoices}
                        exclusive
                        onChange={(_, value) => handleChartTypeChange('totalInvoices', value)}
                    >
                        <ToggleButton value="bar">Bar</ToggleButton>
                        <ToggleButton value="pie">Pie</ToggleButton>
                    </ToggleButtonGroup>
                    <ResponsiveContainer width="100%" height={300}>
                        {renderTotalInvoicesChart()}
                    </ResponsiveContainer>
                </Box>
                <Box>
                    <Typography variant="h6">Overdue Invoices Trend</Typography>
                    <ToggleButtonGroup
                        value={chartType.overdueTrend}
                        exclusive
                        onChange={(_, value) => handleChartTypeChange('overdueTrend', value)}
                    >
                        <ToggleButton value="line">Line</ToggleButton>
                        <ToggleButton value="area">Area</ToggleButton>
                    </ToggleButtonGroup>
                    <ResponsiveContainer width="100%" height={300}>
                        {renderOverdueTrendChart()}
                    </ResponsiveContainer>
                </Box>
            </Box>
        </Box>
    );
};

export default Dashboard;

