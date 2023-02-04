import axios from 'axios'
import { useState, useEffect } from 'react'
import { Box, Container, Paper, Skeleton } from '@mui/material'
import CustomMap from './CustomMap'

function StationDetails({ row }) {
  const [station, setStation] = useState()
  const [loading, setLoading] = useState(true)
  useEffect(() => {
    axios
      .get(`http://localhost:8080/station?id=${row.station_id}`)
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
          <Box sx={{ flexGrow: 2, justifyContent: 'space-between' }}>
            <Paper sx={{ px: 8, py: 8, m: 2 }}>
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
              sx={{ m: 2 }}
              variant='rounded'
              width={440}
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
          <Paper sx={{ px: 8, py: 8 }}>
            <Box>
              <p>
                Asema: {station?.name_fin} {station?.station_id}
              </p>
              <p>Osoite: {station?.address_fin}</p>
              <p>Lähtevien matkojen lukumäärä: {station?.departure_count}</p>
              <p>Saapuvien matkojen lukumäärä: {station?.return_count}</p>
            </Box>
          </Paper>

          <Box sx={{ px: 2, py: 4 }} />
          <CustomMap y={station?.y} x={station?.x} />
        </Box>
      )}
    </div>
  )
}

export default StationDetails
