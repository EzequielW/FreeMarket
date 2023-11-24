import * as React from 'react';
import * as moment from 'moment';

import { styled } from '@mui/material/styles';
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell, { tableCellClasses } from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import Paper from '@mui/material/Paper';
import { CardMedia, Chip } from '@mui/material';

import MoreVertIcon from '@mui/icons-material/MoreVert';

import Product from '../../interfaces/Product';

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

// const rows: Product[] = [
//   {
//     id: 1,
//     name: 'AMD CPU 5600X',
//     image: '/products/38dfb741-3f6a-43db-b23b-1252e11688e4.png',
//     price: 158,
//     created_at: '2023-03-21',
//     status: 1,
//   },
//   {
//     id: 2,
//     name: 'AMD CPU 5600X',
//     image: '/products/38dfb741-3f6a-43db-b23b-1252e11688e4.png',
//     price: 158,
//     created_at: '2023-03-21',
//     status: 1,
//   },
//   {
//     id: 3,
//     name: 'AMD CPU 5600X',
//     image: '/products/38dfb741-3f6a-43db-b23b-1252e11688e4.png',
//     price: 158,
//     created_at: '2023-03-21',
//     status: 1,
//   },
//   {
//     id: 4,
//     name: 'AMD CPU 5600X',
//     image: '/products/38dfb741-3f6a-43db-b23b-1252e11688e4.png',
//     price: 158,
//     created_at: '2023-03-21',
//     status: 1,
//   },
//   {
//     id: 5,
//     name: 'AMD CPU 5600X',
//     image: '/products/38dfb741-3f6a-43db-b23b-1252e11688e4.png',
//     price: 158,
//     created_at: '2023-03-21',
//     status: 1,
//   },
//   {
//     id: 6,
//     name: 'AMD CPU 5600X',
//     image: '/products/38dfb741-3f6a-43db-b23b-1252e11688e4.png',
//     price: 158,
//     created_at: '2023-03-21',
//     status: 1,
//   },
//   {
//     id: 7,
//     name: 'AMD CPU 5600X',
//     image: '/products/38dfb741-3f6a-43db-b23b-1252e11688e4.png',
//     price: 158,
//     created_at: '2023-03-21',
//     status: 1,
//   },
//   {
//     id: 8,
//     name: 'AMD CPU 5600X',
//     image: '/products/38dfb741-3f6a-43db-b23b-1252e11688e4.png',
//     price: 158,
//     created_at: '2023-03-21',
//     status: 1,
//   },
//   {
//     id: 9,
//     name: 'AMD CPU 5600X',
//     image: '/products/38dfb741-3f6a-43db-b23b-1252e11688e4.png',
//     price: 158,
//     created_at: '2023-03-21',
//     status: 1,
//   },
//   {
//     id: 10,
//     name: 'AMD CPU 5600X',
//     image: '/products/38dfb741-3f6a-43db-b23b-1252e11688e4.png',
//     price: 158,
//     created_at: '2023-03-21',
//     status: 1,
//   },
// ];

export default function ProductsTable({ products }) {

  return (
    <TableContainer component={Paper}>
      <Table sx={{ minWidth: 700 }} aria-label="customized table">
        <TableHead>
          <TableRow>
            <StyledTableCell>Product ID</StyledTableCell>
            <StyledTableCell>Product name</StyledTableCell>
            <StyledTableCell>Image</StyledTableCell>
            <StyledTableCell>Price</StyledTableCell>
            <StyledTableCell>Created</StyledTableCell>
            <StyledTableCell>Status</StyledTableCell>
            <StyledTableCell>Action</StyledTableCell>
          </TableRow>
        </TableHead>
        <TableBody>
          {products.map((row: Product) => {
            return (
              <StyledTableRow key={row.id}> 
                <StyledTableCell>{row.id}</StyledTableCell>
                <StyledTableCell>{row.name}</StyledTableCell>
                <StyledTableCell>
                    <CardMedia
                        component="img"
                        sx={{ width: 40, height: 40 }}
                        image={`${process.env.REACT_APP_SERVER_URL}public${row.imagePath}`}
                        alt="Product image"
                    />
                </StyledTableCell>
                <StyledTableCell>${row.price.toFixed(2)}</StyledTableCell>
                <StyledTableCell>{moment(row.createdAt).format('DD/MM/YYYY')}</StyledTableCell>
                <StyledTableCell>
                  { row.status === 1 || !row.status ? 
                    <Chip variant="outlined" label='Enabled' color='success' /> : 
                    <Chip variant="outlined" label='Disabled' color='error' />
                  }
                </StyledTableCell>
                <StyledTableCell><MoreVertIcon /></StyledTableCell>
              </StyledTableRow>
            )
          })}
        </TableBody>
      </Table>
    </TableContainer>
  );
}