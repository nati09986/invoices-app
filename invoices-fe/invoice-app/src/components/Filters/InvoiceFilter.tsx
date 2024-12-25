import React, { useState } from 'react';
import { FilterCriteria } from '../../types/filterTypes';

interface Props {
  onFilterChange: (filters: FilterCriteria) => void;
}

const InvoiceFilter: React.FC<Props> = ({ onFilterChange }) => {
  const [filters, setFilters] = useState<FilterCriteria>({
    startDate: '',
    endDate: '',
    status: '',
    customer: '',
  });

  const handleInputChange = (e: React.ChangeEvent<HTMLInputElement | HTMLSelectElement>) => {
    const { name, value } = e.target;
    const updatedFilters = { ...filters, [name]: value };
    setFilters(updatedFilters);
    onFilterChange(updatedFilters);
  };

  return (
    <div>
      <label>
        Start Date:
        <input type="date" name="startDate" value={filters.startDate} onChange={handleInputChange} />
      </label>
      <label>
        End Date:
        <input type="date" name="endDate" value={filters.endDate} onChange={handleInputChange} />
      </label>
      <label>
        Status:
        <select name="status" value={filters.status} onChange={handleInputChange}>
          <option value="">All</option>
          <option value="Paid">Paid</option>
          <option value="Overdue">Overdue</option>
          <option value="Pending">Pending</option>
        </select>
      </label>
      <label>
        Customer:
        <input
          type="text"
          name="customer"
          value={filters.customer}
          placeholder="Customer Name"
          onChange={handleInputChange}
        />
      </label>
    </div>
  );
};

export default InvoiceFilter;
