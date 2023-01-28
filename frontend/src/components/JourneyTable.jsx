import axios from "axios"
import { useState, useEffect } from "react"
import { Table, TableBody, TableCell, TableRow } from "@mui/material"

function JourneyTable() {
  const [journeys, setJourneys] = useState({ content: [] })

  useEffect(() => {
    axios
      .get("http://localhost:8080/journeys")
      .then((res) => setJourneys(res.data))
      .catch((err) => console.error(err))
  }, [])

  console.log(journeys)

  return (
    <Table>
      <TableBody>
        {journeys.content.map((journey) => (
          <TableRow key={journey.id}>
            <TableCell>{journey.departure_time}</TableCell>
            <TableCell>{journey.return_time}</TableCell>
            <TableCell>{journey.departure_station_name}</TableCell>
            <TableCell>{journey.return_station_name}</TableCell>
            <TableCell>{journey.distance}</TableCell>
            <TableCell>{journey.duration}</TableCell>
          </TableRow>
        ))}
      </TableBody>
    </Table>
  )
}

export default JourneyTable
