import {
  Box,
  Table,
  TableBody,
  TableCell,
  TableContainer,
  TableRow,
  TablePagination,
} from '@mui/material'
import usePaginatedData from '../utils/usePaginatedData'

function JourneyTable() {
  const { content, paginationProps } = usePaginatedData('journeys')

  const secondsToMinutes = (seconds) =>
    Math.floor(seconds / 60) + ':' + ('0' + Math.floor(seconds % 60)).slice(-2)

  const metersToKilometers = (meters) =>
    new Intl.NumberFormat('fi-FI', {
      style: 'unit',
      unit: 'kilometer',
      maximumFractionDigits: 2,
    }).format(meters / 1000)

  return (
    <Box>
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
            {content.map((row) => (
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
    </Box>
  )
}

export default JourneyTable
