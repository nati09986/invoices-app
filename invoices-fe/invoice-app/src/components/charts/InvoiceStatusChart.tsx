import React from 'react';
import { PieChart, Pie, Cell, Tooltip, Legend } from 'recharts';
import { useSelector } from 'react-redux';
import { RootState } from '../../store/store';


const COLORS = ['#0088FE', '#00C49F', '#FFBB28', '#FF8042'];

const InvoiceStatusChart: React.FC = () => {
  const data = useSelector((state: RootState) => state.invoices.data);

  const aggregatedData = data.reduce((acc: any, invoice: any) => {
    acc[invoice.status] = (acc[invoice.status] || 0) + invoice.amount;
    return acc;
  }, {});

  const chartData = Object.keys(aggregatedData).map((key) => ({
    name: key,
    value: aggregatedData[key],
  }));

  return (
    <PieChart width={400} height={400}>
      <Pie data={chartData} dataKey="value" nameKey="name" cx="50%" cy="50%" outerRadius={150} fill="#8884d8">
        {chartData.map((entry, index) => (
          <Cell key={`cell-${index}`} fill={COLORS[index % COLORS.length]} />
        ))}
      </Pie>
      <Tooltip />
      <Legend />
    </PieChart>
  );
};

export default InvoiceStatusChart;
