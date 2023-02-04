import { useState } from 'react'
import {
  Box,
  Table,
  TableBody,
  TableCell,
  TableContainer,
  TableRow,
  TablePagination,
  Button,
  Skeleton,
  Paper,
} from '@mui/material'
import StationDetails from './StationDetails'
import usePaginatedData from '../utils/usePaginatedData'

function StationTable() {
  const { content, loading, paginationProps } = usePaginatedData('stations')

  const ExpandableTableRow = ({ children, expandComponent }) => {
    const [isExpanded, setIsExpanded] = useState(false)

    return (
      <>
        <TableRow>
          {children}
          <TableCell padding='checkbox'>
            <Button
              variant='contained'
              onClick={() => setIsExpanded(!isExpanded)}>
              {isExpanded ? 'piilota' : 'näytä'}
            </Button>
          </TableCell>
        </TableRow>
        {isExpanded && (
          <TableRow>
            <TableCell colSpan={6}>{expandComponent}</TableCell>
          </TableRow>
        )}
      </>
    )
  }

  const skeletonArray = Array(paginationProps.rowsPerPage).fill('')

  return (
    <Paper sx={{ px: 4, py: 4 }}>
      <TableContainer>
        <Table>
          <TableBody>
            <TableRow>
              <TableCell>Nimi</TableCell>
              <TableCell>Osoite</TableCell>
              <TableCell>Kaupunki</TableCell>
              <TableCell>Operaattori</TableCell>
              <TableCell>Kapasiteetti</TableCell>
              <TableCell>Lisätietoja</TableCell>
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
                <ExpandableTableRow
                  key={row.name_fin}
                  expandComponent={<StationDetails row={row}></StationDetails>}>
                  <TableCell>{row.name_fin}</TableCell>
                  <TableCell>{row.address_fin}</TableCell>
                  <TableCell>{row.city_fin}</TableCell>
                  <TableCell>{row.operator}</TableCell>
                  <TableCell>{row.capacity}</TableCell>
                </ExpandableTableRow>
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

export default StationTable
