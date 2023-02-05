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
import usePaginatedData from '../utils/usePaginatedData'
import SortTableHead from './SortTableHead'

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
  const [order, setOrder] = useState('asc')
  const [orderBy, setOrderBy] = useState('nameFin')
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
      <TableContainer>
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
                  </TableRow>
                ))
              : content &&
                content.map((row) => (
                  <TableRow key={row.id}>
                    <TableCell>{row.departureStationName}</TableCell>
                    <TableCell>{row.returnStationName}</TableCell>
                    <TableCell>{metersToKilometers(row.distance)}</TableCell>
                    <TableCell>{secondsToMinutes(row.duration)} min</TableCell>
                  </TableRow>
                ))}
          </TableBody>
        </Table>
      </TableContainer>
      <Box>
        <TablePagination {...paginationProps} />
      </Box>
    </Paper>
  )
}

export default JourneyTable
