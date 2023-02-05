import axios from 'axios'
import { useState, useEffect } from 'react'
import { Box, Container, Paper, Skeleton } from '@mui/material'
import CustomMap from './CustomMap'

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
        <Box sx={{ display: 'flex' }}>
          <Box
            sx={{ flexGrow: 2, justifyContent: 'space-between' }}
            minWidth={360}
            height={280}>
            <Paper sx={{ px: 8, py: 8, m: 2, flexShrink: 0 }}>
              <p>
                <Skeleton />
              </p>
              <p>
                <Skeleton />
              </p>
              <p>
                <Skeleton />
              </p>
              <p>
                <Skeleton />
              </p>
            </Paper>
          </Box>

          <Box>
            <Skeleton
              sx={{ m: 2, width: '100%' }}
              variant='rectangular'
              width={800}
              height={280}
            />
          </Box>
        </Box>
      )}
      {station && (
        <Box
          sx={{
            display: 'flex',
            justifyContent: 'space-between',
            px: 2,
            py: 2,
          }}>
          <Paper sx={{ px: 8, py: 8, mr: 4 }} maxWidth={360}>
            <Box>
              <p>
                Asema: {station?.nameFin} {station?.stationId}
              </p>
              <p>Osoite: {station?.addressFin}</p>
              <p>Lähtevien matkojen lukumäärä: {station?.departureCount}</p>
              <p>Saapuvien matkojen lukumäärä: {station?.returnCount}</p>
            </Box>
          </Paper>
          <CustomMap
            sx={{ width: '100%', px: 2 }}
            y={station?.y}
            x={station?.x}
          />
        </Box>
      )}
    </div>
  )
}

export default StationDetails
