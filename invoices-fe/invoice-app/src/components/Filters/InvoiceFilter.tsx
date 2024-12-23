import React from 'react';

interface Props {
  onFilterChange: (filters: { startDate: string; endDate: string; status: string; customer: string }) => void;
}

const InvoiceFilter: React.FC<Props> = ({ onFilterChange }) => {
  const handleInputChange = (e: React.ChangeEvent<HTMLInputElement | HTMLSelectElement>) => {
    const { name, value } = e.target;
    onFilterChange((prev) => ({ ...prev, [name]: value }));
  };

  return (
    <div>
      <input type="date" name="startDate" onChange={handleInputChange} />
      <input type="date" name="endDate" onChange={handleInputChange} />
      <select name="status" onChange={handleInputChange}>
        <option value="">All</option>
        <option value="Paid">Paid</option>
        <option value="Overdue">Overdue</option>
        <option value="Pending">Pending</option>
      </select>
      <input type="text" name="customer" placeholder="Customer Name" onChange={handleInputChange} />
    </div>
  );
};

export default InvoiceFilter;
