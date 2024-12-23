import React from 'react';
import { LineChart, Line, XAxis, YAxis, Tooltip, CartesianGrid } from 'recharts';

interface Props {
  data: { date: string; overdue: number }[];
}

const OverdueTrendChart: React.FC<Props> = ({ data }) => (
  <LineChart width={600} height={300} data={data}>
    <CartesianGrid strokeDasharray="3 3" />
    <XAxis dataKey="date" />
    <YAxis />
    <Tooltip />
    <Line type="monotone" dataKey="overdue" stroke="#ff7300" />
  </LineChart>
);

export default OverdueTrendChart;
