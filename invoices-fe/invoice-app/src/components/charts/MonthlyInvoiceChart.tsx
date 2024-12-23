import React from 'react';
import { BarChart, Bar, XAxis, YAxis, Tooltip, CartesianGrid } from 'recharts';

interface Props {
  data: { month: string; total: number }[];
}

const MonthlyInvoiceChart: React.FC<Props> = ({ data }) => (
  <BarChart width={600} height={300} data={data}>
    <CartesianGrid strokeDasharray="3 3" />
    <XAxis dataKey="month" />
    <YAxis />
    <Tooltip />
    <Bar dataKey="total" fill="#8884d8" />
  </BarChart>
);

export default MonthlyInvoiceChart;
