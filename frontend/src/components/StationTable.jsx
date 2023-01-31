import axios from 'axios'
import { useState, useEffect } from 'react'
import {
  Box,
  Table,
  TableBody,
  TableCell,
  TableContainer,
  TableRow,
} from '@mui/material'

function StationTable() {
  const [stations, setStations] = useState({ content: [] })

  useEffect(() => {
    axios
      .get('http://localhost:8080/stations')
      .then((res) => setStations(res.data))
      .catch((err) => console.error(err))
  }, [])

  const rows = stations.content
  console.log(rows)

  return (
    <Box>
      <TableContainer>
        <Table>
          <TableBody>
            <TableRow>
              <TableCell>Aseman ID</TableCell>
              <TableCell>Nimi</TableCell>
              <TableCell>Osoite</TableCell>
              <TableCell>Kaupunki</TableCell>
              <TableCell>Operaattori</TableCell>
              <TableCell>Kapasiteetti</TableCell>
            </TableRow>
            {rows.map((station) => (
              <TableRow key={station.fid}>
                <TableCell width={300}>{station.station_id}</TableCell>
                <TableCell width={300}>{station.name_fin}</TableCell>
                <TableCell width={300}>{station.address_fin}</TableCell>
                <TableCell width={300}>{station.city_fin}</TableCell>
                <TableCell width={300}>{station.operator}</TableCell>
                <TableCell width={300}>{station.capacity}</TableCell>
              </TableRow>
            ))}
          </TableBody>
        </Table>
      </TableContainer>
    </Box>
  )
}

export default StationTable
