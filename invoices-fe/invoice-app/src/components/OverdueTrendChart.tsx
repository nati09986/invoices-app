import React from 'react';
import { Box, Typography, ToggleButton, ToggleButtonGroup } from '@mui/material';
import { LineChart, Line, AreaChart, Area, XAxis, YAxis, Tooltip, Legend, ResponsiveContainer } from 'recharts';
import { Invoice } from '../types/invoice';

interface OverdueTrendChartProps {
    data: Invoice[];
    chartType: 'line' | 'area';
    onChartTypeChange: (value: 'line' | 'area') => void;
}

const OverdueTrendChart: React.FC<OverdueTrendChartProps> = ({ data, chartType, onChartTypeChange }) => {
    const overdueData = data
        .filter(invoice => new Date(invoice.invoiceDueDate) < new Date())
        .reduce<Record<string, number>>((acc, invoice) => {
            const date = invoice.invoiceDueDate.split('T')[0];
            acc[date] = (acc[date] || 0) + invoice.invoiceCost;
            return acc;
        }, {});

    const chartData = Object.entries(overdueData).map(([date, overdueAmount]) => ({ date, overdueAmount }));

    return (
        <Box>
            <Typography variant="h6">Overdue Invoices Trend</Typography>
            <ToggleButtonGroup
                value={chartType}
                exclusive
                onChange={(_, value) => value && onChartTypeChange(value)}
            >
                <ToggleButton value="line">Line</ToggleButton>
                <ToggleButton value="area">Area</ToggleButton>
            </ToggleButtonGroup>
            <ResponsiveContainer width="100%" height={300}>
                {chartType === 'line' ? (
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
                )}
            </ResponsiveContainer>
        </Box>
    );
};

export default OverdueTrendChart;