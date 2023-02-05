import {
  Box,
  Table,
  TableBody,
  TableCell,
  TableContainer,
  TableRow,
  TablePagination,
  Skeleton,
  Paper,
} from '@mui/material'
import { useState } from 'react'
import { theme } from '../themes/theme'
import usePaginatedData from '../utils/usePaginatedData'
import SortTableHead from './custom/SortTableHead'
import StyledTableRow from './custom/StyledTableRow'

const headCells = [
  {
    id: 'departureStationName',
    numeric: true,
    disablePadding: false,
    label: 'Lähtöasema',
  },
  {
    id: 'returnStationName',
    numeric: true,
    disablePadding: false,
    label: 'Määränpää',
  },
  {
    id: 'distance',
    numeric: true,
    disablePadding: false,
    label: 'Matka',
  },
  {
    id: 'duration',
    numeric: true,
    disablePadding: false,
    label: 'Kesto',
  },
]

function JourneyTable() {
  const [order, setOrder] = useState('desc')
  const [orderBy, setOrderBy] = useState('departureStationName')
  const { content, loading, paginationProps, sortingProps } =
    usePaginatedData('')

  const handleRequestSort = (_, property) => {
    const isAsc = orderBy === property && order === 'asc'
    setOrder(isAsc ? 'desc' : 'asc')
    setOrderBy(property)
    sortingProps.onSortChange(property)
    sortingProps.onOrderChange(order)
  }

  const secondsToMinutes = (seconds) =>
    Math.floor(seconds / 60) + ':' + ('0' + Math.floor(seconds % 60)).slice(-2)

  const metersToKilometers = (meters) =>
    new Intl.NumberFormat('fi-FI', {
      style: 'unit',
      unit: 'kilometer',
      maximumFractionDigits: 2,
    }).format(meters / 1000)

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
            />
            {loading
              ? skeletonArray.map((_, index) => (
                  <StyledTableRow styled={'theme'} key={index}>
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
                  </StyledTableRow>
                ))
              : content &&
                content.map((row) => (
                  <StyledTableRow styled={theme} key={row.id}>
                    <TableCell>{row.departureStationName}</TableCell>
                    <TableCell>{row.returnStationName}</TableCell>
                    <TableCell>{metersToKilometers(row.distance)}</TableCell>
                    <TableCell>{secondsToMinutes(row.duration)} min</TableCell>
                  </StyledTableRow>
                ))}
          </TableBody>
        </Table>{' '}
        <Box>
          <TablePagination {...paginationProps} />
        </Box>
      </TableContainer>
    </Paper>
  )
}

export default JourneyTable
