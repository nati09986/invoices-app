import React, { useEffect, useState } from 'react';
import { CircularProgress, Typography, Box } from '@mui/material';
import { BarChart, Bar, XAxis, YAxis, Tooltip, Legend, ResponsiveContainer } from 'recharts';
import ApiService from '../services/ApiService';

interface InvoiceStatusSummary {
    status: string;
    totalAmount: number;
}

const apiService = new ApiService('http://localhost:8080/api');

const Dashboard: React.FC = () => {
    const [data, setData] = useState<InvoiceStatusSummary[]>([]);
    const [loading, setLoading] = useState<boolean>(true);
    const [error, setError] = useState<string | null>(null);

    const fetchData = async () => {
        setLoading(true);
        setError(null);

        try {
            const invoices = await apiService.fetchInvoices(
                // '2024-09-01',
                // '2024-09-19',
                // 'LILI Avraham',
                // 'CONFIRMED'
            );

            const summary = invoices.reduce<Record<string, number>>((acc, invoice) => {
                const status = invoice.invoiceStatus || 'UNKNOWN';
                acc[status] = (acc[status] || 0) + invoice.invoiceCost;
                return acc;
            }, {});

            const chartData = Object.entries(summary).map(([status, totalAmount]) => ({
                status,
                totalAmount,
            }));

            setData(chartData);
        } catch (error) {
            console.error('Error fetching data:', error);
            setError('Failed to load data. Please try again later.');
        } finally {
            setLoading(false);
        }
    };

    useEffect(() => {
        fetchData();
    }, []);

    if (loading) {
        return (
            <Box display="flex" justifyContent="center" alignItems="center" height="100vh">
                <CircularProgress />
            </Box>
        );
    }

    if (error) {
        return (
            <Box display="flex" justifyContent="center" alignItems="center" height="100vh">
                <Typography color="error">{error}</Typography>
            </Box>
        );
    }

    return (
        <Box>
            <Typography variant="h4" align="center" gutterBottom>
                Invoice Status Summary
            </Typography>
            <ResponsiveContainer width="100%" height={400}>
                <BarChart data={data} margin={{ top: 20, right: 30, left: 20, bottom: 5 }}>
                    <XAxis dataKey="status" />
                    <YAxis />
                    <Tooltip />
                    <Legend />
                    <Bar dataKey="totalAmount" fill="#8884d8" />
                </BarChart>
            </ResponsiveContainer>
        </Box>
    );
};

export default Dashboard;
