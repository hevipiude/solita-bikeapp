import { useState } from 'react'
import {
  Box,
  Table,
  TableBody,
  TableCell,
  TableContainer,
  TableRow,
  TablePagination,
  Button,
  Skeleton,
  Paper,
} from '@mui/material'
import StationDetails from './StationDetails'
import usePaginatedData from '../utils/usePaginatedData'
import SortTableHead from './custom/SortTableHead'
import StyledTableRow from './custom/StyledTableRow'
import { theme } from '../themes/theme'
import ExpandableTableRow from './custom/ExpandableTableRow'

const headCells = [
  {
    id: 'nameFin',
    numeric: false,
    disablePadding: true,
    label: 'Nimi',
  },
  {
    id: 'addressFin',
    numeric: true,
    disablePadding: false,
    label: 'Osoite',
  },
  {
    id: 'cityFin',
    numeric: true,
    disablePadding: false,
    label: 'Kaupunki',
  },
  {
    id: 'operator',
    numeric: true,
    disablePadding: false,
    label: 'Operaattori',
  },
  {
    id: 'capacity',
    numeric: true,
    disablePadding: false,
    label: 'Kapasiteetti',
  },
]

function StationTable() {
  const [order, setOrder] = useState('desc')
  const [orderBy, setOrderBy] = useState('nameFin')
  const { content, loading, paginationProps, sortingProps } =
    usePaginatedData('stations')

  const handleRequestSort = (_, property) => {
    const isAsc = orderBy === property && order === 'asc'
    setOrder(isAsc ? 'desc' : 'asc')
    setOrderBy(property)
    sortingProps.onSortChange(property)
    sortingProps.onOrderChange(isAsc ? 'desc' : 'asc')
    console.log(order)
  }

  const skeletonArray = Array(paginationProps.rowsPerPage).fill('')

  return (
    <Paper sx={{ px: 4, py: 4 }}>
      <TableContainer component={Paper}>
        <Table>
          <TableBody>
            <SortTableHead
              order={order}
              orderBy={orderBy}
              onRequestSort={handleRequestSort}
              rowCount={content.length}
              headCells={headCells}
              extra='Lisätietoja'
            />
            {loading
              ? skeletonArray.map((_, index) => (
                  <TableRow key={index}>
                    <TableCell>
                      <Skeleton />
                    </TableCell>
                    <TableCell>
                      <Skeleton />
                    </TableCell>
                    <TableCell>
                      <Skeleton />
                    </TableCell>
                    <TableCell>
                      <Skeleton />
                    </TableCell>
                    <TableCell>
                      <Skeleton />
                    </TableCell>
                  </TableRow>
                ))
              : content.map((row) => (
                  <ExpandableTableRow
                    key={row.nameFin}
                    expandComponent={
                      <StationDetails row={row}></StationDetails>
                    }>
                    {console.log(row.nameFin)}
                    <TableCell>{row.nameFin}</TableCell>
                    <TableCell>{row.addressFin}</TableCell>
                    <TableCell>{row.cityFin}</TableCell>
                    <TableCell>{row.operator}</TableCell>
                    <TableCell>{row.capacity}</TableCell>
                  </ExpandableTableRow>
                ))}
          </TableBody>
        </Table>
        <Box>
          <TablePagination {...paginationProps} />
        </Box>
      </TableContainer>
    </Paper>
  )
}

export default StationTable
