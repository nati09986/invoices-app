import React from 'react';
import { BarChart, Bar, XAxis, YAxis, Tooltip, CartesianGrid } from 'recharts';

interface Props {
  data: { customer: string; total: number }[];
}

const CustomerAnalysisChart: React.FC<Props> = ({ data }) => (
  <BarChart width={600} height={300} data={data} layout="vertical">
    <CartesianGrid strokeDasharray="3 3" />
    <XAxis type="number" />
    <YAxis dataKey="customer" type="category" />
    <Tooltip />
    <Bar dataKey="total" fill="#82ca9d" />
  </BarChart>
);

export default CustomerAnalysisChart;
