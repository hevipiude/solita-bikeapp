import axios from 'axios'
import { useState, useEffect } from 'react'
import {
  Box,
  Table,
  TableBody,
  TableCell,
  TableContainer,
  TableRow,
  TablePagination,
} from '@mui/material'

function JourneyTable() {
  const [journeys, setJourneys] = useState({ content: [] })

  useEffect(() => {
    axios
      .get('http://localhost:8080/journeys')
      .then((res) => setJourneys(res.data))
      .catch((err) => console.error(err))
  }, [])

  const rows = journeys.content
  console.log(rows)

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
            {rows.map((journey) => (
              <TableRow key={journey.id}>
                <TableCell>{journey.id}</TableCell>
                <TableCell>{journey.departure_station_name}</TableCell>
                <TableCell>{journey.return_station_name}</TableCell>
                <TableCell>{metersToKilometers(journey.distance)}</TableCell>
                <TableCell>{secondsToMinutes(journey.duration)}</TableCell>
              </TableRow>
            ))}
          </TableBody>
        </Table>
      </TableContainer>
    </Box>
  )
}

export default JourneyTable
