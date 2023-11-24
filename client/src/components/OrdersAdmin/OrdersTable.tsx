import * as React from 'react';
import { styled } from '@mui/material/styles';
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell, { tableCellClasses } from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import Paper from '@mui/material/Paper';

import MoreVertIcon from '@mui/icons-material/MoreVert';
import { Chip } from '@mui/material';
import Order from '../../interfaces/Order';

const StyledTableCell = styled(TableCell)(({ theme }) => ({
  [`&.${tableCellClasses.head}`]: {
    backgroundColor: theme.palette.common.black,
    color: theme.palette.common.white,
  },
  [`&.${tableCellClasses.body}`]: {
    fontSize: 14,
  },
}));

const StyledTableRow = styled(TableRow)(({ theme }) => ({
  '&:nth-of-type(odd)': {
    backgroundColor: theme.palette.action.hover,
  },
  // hide last border
  '&:last-child td, &:last-child th': {
    border: 0,
  },
}));

let rows: Order[] = [
  {
    id: 1,
    customer: 'hmerlino@gmail.com',
    total_products: 3,
    total_price: 694,
    created_at: '2023-03-21',
    status: 'APPROVED',
  },
  {
    id: 2,
    customer: 'glopez@gmail.com',
    total_products: 14,
    total_price: 934,
    created_at: '2023-03-24',
    status: 'APPROVED',
  },
  {
    id: 3,
    customer: 'jwilson@gmail.com',
    total_products: 6,
    total_price: 1449.99,
    created_at: '2023-03-23',
    status: 'PENDING',
  },
  {
    id: 4,
    customer: 'gforeman@hotmail.com',
    total_products: 1,
    total_price: 354,
    created_at: '2023-03-23',
    status: 'APPROVED',
  },
  {
    id: 5,
    customer: 'lsmith@gmail.com',
    total_products: 1,
    total_price: 144.99,
    created_at: '2023-04-22',
    status: 'CANCELED',
  },
  {
    id: 6,
    customer: 'hmerlino@gmail.com',
    total_products: 3,
    total_price: 694,
    created_at: '2023-03-21',
    status: 'APPROVED',
  },
  {
    id: 7,
    customer: 'glopez@gmail.com',
    total_products: 14,
    total_price: 934,
    created_at: '2023-03-24',
    status: 'APPROVED',
  },
  {
    id: 8,
    customer: 'jwilson@gmail.com',
    total_products: 6,
    total_price: 1449.99,
    created_at: '2023-03-23',
    status: 'PENDING',
  },
  {
    id: 9,
    customer: 'gforeman@hotmail.com',
    total_products: 1,
    total_price: 354,
    created_at: '2023-03-23',
    status: 'APPROVED',
  },
  {
    id: 10,
    customer: 'lsmith@gmail.com',
    total_products: 1,
    total_price: 144.99,
    created_at: '2023-04-22',
    status: 'CANCELED',
  },
];

const OrdersTable = ({ latest = false }) => {
  const statusChip = (status: string) => {
    switch(status) {
      case 'APPROVED':
        return { name: 'Delivered', color: 'success' };
      case 'PENDING':
        return { name: 'Pending', color: 'warning' };
      case 'CANCELED':
        return { name: 'Canceled', color: 'error' };
      default:
        return { name: status, color: 'primary' };
    }
  }

  const currentRows = () => {
    if(latest) {
      return rows.slice(0, 5);
    }
    else {
      return rows;
    }
  }

  return (
    <TableContainer component={Paper}>
      <Table sx={{ minWidth: 700 }} aria-label="customized table">
        <TableHead>
          <TableRow>
            <StyledTableCell>Order ID</StyledTableCell>
            <StyledTableCell>Customer</StyledTableCell>
            <StyledTableCell>Products</StyledTableCell>
            <StyledTableCell>Total price</StyledTableCell>
            <StyledTableCell>Date</StyledTableCell>
            <StyledTableCell>Status</StyledTableCell>
            <StyledTableCell>Action</StyledTableCell>
          </TableRow>
        </TableHead>
        <TableBody>
          {
            currentRows().map((row) => {
              const statusValue = statusChip(row.status);

              return (
                <StyledTableRow key={row.id}>
                  <StyledTableCell>{row.id}</StyledTableCell>
                  <StyledTableCell>{row.customer}</StyledTableCell>
                  <StyledTableCell>{row.total_products}</StyledTableCell>
                  <StyledTableCell>${row.total_price.toFixed(2)}</StyledTableCell>
                  <StyledTableCell>{row.created_at}</StyledTableCell>
                  <StyledTableCell><Chip variant="outlined" label={statusValue.name} color={statusValue.color as any} /></StyledTableCell>
                  <StyledTableCell><MoreVertIcon /></StyledTableCell>
                </StyledTableRow>
              );
            })
          }
        </TableBody>
      </Table>
    </TableContainer>
  );
}

export default OrdersTable;