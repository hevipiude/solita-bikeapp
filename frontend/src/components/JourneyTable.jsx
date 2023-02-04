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
import usePaginatedData from '../utils/usePaginatedData'

function JourneyTable() {
  const { content, loading, paginationProps } = usePaginatedData('')

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
            <TableRow>
              <TableCell>ID</TableCell>
              <TableCell>Lähtöasema</TableCell>
              <TableCell>Saapumisasema</TableCell>
              <TableCell>Matka</TableCell>
              <TableCell>Aika</TableCell>
            </TableRow>
            {loading &&
              skeletonArray.map((_, index) => (
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
              ))}
            {content &&
              content.map((row) => (
                <TableRow key={row.id}>
                  <TableCell>{row.id}</TableCell>
                  <TableCell>{row.departure_station_name}</TableCell>
                  <TableCell>{row.return_station_name}</TableCell>
                  <TableCell>{metersToKilometers(row.distance)}</TableCell>
                  <TableCell>{secondsToMinutes(row.duration)}</TableCell>
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
