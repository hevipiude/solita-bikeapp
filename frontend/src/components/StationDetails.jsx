import axios from 'axios'
import { useState, useEffect } from 'react'
import { Container } from '@mui/material'

function StationDetails({ row }) {
  const [station, setStation] = useState()

  useEffect(() => {
    axios
      .get(`http://localhost:8080/s_station?id=${row.station_id}`)
      .then((res) => setStation(res.data))
      .catch((err) => console.error(err))
  }, [])

  return (
    <Container>
      <h1>
        Asema: {station?.name_fin} {station?.id}
      </h1>
      <h3>Osoite: {station?.address_fin}</h3>
      <h3>Lähtevien matkojen lukumäärä: {station?.departure_count}</h3>
      <h3>Saapuvien matkojen lukumäärä: {station?.return_count}</h3>
    </Container>
  )
}

export default StationDetails
