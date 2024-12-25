import React from 'react';
import { Box, Typography, ToggleButton, ToggleButtonGroup } from '@mui/material';
import { PieChart, Pie, Cell, BarChart, Bar, XAxis, YAxis, Tooltip, Legend, ResponsiveContainer } from 'recharts';
import { Invoice } from '../types/invoice';

interface TotalInvoicesChartProps {
  data: Invoice[];
  chartType: 'bar' | 'pie';
  onChartTypeChange: (value: 'bar' | 'pie') => void;
}

const TotalInvoicesChart: React.FC<TotalInvoicesChartProps> = ({ data, chartType, onChartTypeChange }) => {
  const totals = data.reduce<Record<string, number>>((acc, invoice) => {
    acc[invoice.invoiceStatus] = (acc[invoice.invoiceStatus] || 0) + invoice.invoiceCost;
    return acc;
  }, {});

  const chartData = Object.entries(totals).map(([status, total]) => ({ status, total }));

  return (
    <Box>
      <Typography variant="h6">Total Invoice Amounts by Status</Typography>
      <ToggleButtonGroup
        value={chartType}
        exclusive
        onChange={(_, value) => value && onChartTypeChange(value)}
      >
        <ToggleButton value="bar">Bar</ToggleButton>
        <ToggleButton value="pie">Pie</ToggleButton>
      </ToggleButtonGroup>
      <ResponsiveContainer width="100%" height={300}>
        {chartType === 'bar' ? (
          <BarChart data={chartData}>
            <XAxis dataKey="status" />
            <YAxis />
            <Tooltip />
            <Legend />
            <Bar dataKey="total" fill="#8884d8" />
          </BarChart>
        ) : (
          <PieChart>
            <Pie
              data={chartData}
              dataKey="total"
              nameKey="status"
              cx="50%"
              cy="50%"
              outerRadius={120}
              label={(entry) => `${entry.status}: ${entry.total}`}
            >
              {chartData.map((_, index) => (
                <Cell key={`cell-${index}`} fill={['#8884d8', '#82ca9d', '#ffc658'][index % 3]} />
              ))}
            </Pie>
          </PieChart>
        )}
      </ResponsiveContainer>
    </Box>
  );
};

export default TotalInvoicesChart;