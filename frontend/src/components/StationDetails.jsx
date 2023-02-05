import axios from 'axios'
import { useState, useEffect } from 'react'
import { Box, Container, Paper, Skeleton } from '@mui/material'
import CustomMap from './custom/CustomMap'

function StationDetails({ row }) {
  const [station, setStation] = useState()
  const [loading, setLoading] = useState(true)
  useEffect(() => {
    axios
      .get(`http://localhost:8080/station?id=${row.stationId}`)
      .then((res) => {
        setStation(res.data)
        setLoading(false)
      })
      .catch((err) => console.error(err))
  }, [])

  return (
    <div>
      {loading && (
        <Box sx={{ display: 'flex', width: '100%' }}>
          <Box sx={{ mr: 2 }}>
            <Paper sx={{ px: 8, py: 8, ml: 2, mt: 2 }}>
              <p>
                <Skeleton width={236} />
              </p>
              <p>
                <Skeleton width={236} />
              </p>
              <p>
                <Skeleton width={236} />
              </p>
              <p>
                <Skeleton width={236} />
              </p>
            </Paper>
          </Box>

          <Box sx={{ m: 2, flexGrow: 2 }}>
            <Skeleton variant='rectangular' height={280} />
          </Box>
        </Box>
      )}
      {station && (
        <Box
          sx={{
            display: 'flex',
            px: 2,
            py: 2,
          }}>
          <Paper
            sx={{
              px: 8,
              py: 8,
              mr: 2,
              flexGrow: 0,
              height: 152,
              width: 236,
            }}>
            <p>
              Asema: {station?.nameFin} {station?.stationId}
            </p>
            <p>Osoite: {station?.addressFin}</p>
            <p>Lähtevien matkojen lukumäärä: {station?.departureCount}</p>
            <p>Saapuvien matkojen lukumäärä: {station?.returnCount}</p>
          </Paper>
          <Box sx={{ ml: 2, flexGrow: 1 }}>
            <CustomMap sx={{ px: 2 }} y={station?.y} x={station?.x} />
          </Box>
        </Box>
      )}
    </div>
  )
}

export default StationDetails
