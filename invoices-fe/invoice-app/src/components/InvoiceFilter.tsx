import React from 'react';
import { Box, TextField, MenuItem } from '@mui/material';
import { DateRange } from '../types/dateRange';

interface FiltersProps {
    dateRange: DateRange;
    status: string;
    customer: string;
    onDateRangeChange: (dateRange: DateRange) => void;
    onStatusChange: (status: string) => void;
    onCustomerChange: (customer: string) => void;
}

const InvoiceFilters: React.FC<FiltersProps> = ({
    dateRange,
    status,
    customer,
    onDateRangeChange,
    onStatusChange,
    onCustomerChange
}) => (
    <Box display="flex" gap={2} mb={4}>
        <TextField
            label="Start Date"
            type="date"
            value={dateRange.startDate}
            onChange={(e) => onDateRangeChange({ ...dateRange, startDate: e.target.value })}
            InputLabelProps={{ shrink: true }}
        />
        <TextField
            label="End Date"
            type="date"
            value={dateRange.endDate}
            onChange={(e) => onDateRangeChange({ ...dateRange, endDate: e.target.value })}
            InputLabelProps={{ shrink: true }}
        />
        <TextField
            label="Invoice Status"
            select
            value={status}
            onChange={(e) => onStatusChange(e.target.value)}
            style={{ width: '15vw' }}
        >
            <MenuItem value="">Clear</MenuItem>
            <MenuItem value="CONFIRMED">Confirmed</MenuItem>
            <MenuItem value="CANCELLED">Cancelled</MenuItem>
        </TextField>
        <TextField
            label="Customer"
            value={customer}
            onChange={(e) => onCustomerChange(e.target.value)}
        />
    </Box>
);

export default InvoiceFilters;
