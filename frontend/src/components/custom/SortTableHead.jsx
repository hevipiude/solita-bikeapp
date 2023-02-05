import { Box, TableCell, TableRow, TableSortLabel } from '@mui/material'
import PropTypes from 'prop-types'
import { visuallyHidden } from '@mui/utils'
import StyledTableRow from './StyledTableRow'
import { theme } from '../../themes/theme'

export default function SortTableHead(props) {
  const { order, orderBy, onRequestSort, headCells, extra } = props

  const createSortHandler = (property) => (event) => {
    onRequestSort(event, property)
  }

  return (
    <StyledTableRow styled={theme}>
      {headCells.map((headCell) => (
        <TableCell
          key={headCell.id}
          sortDirection={orderBy === headCell.id ? order : false}>
          <TableSortLabel
            active={orderBy === headCell.id}
            direction={orderBy === headCell.id ? order : 'asc'}
            onClick={createSortHandler(headCell.id)}>
            {headCell.label}
            {orderBy === headCell.id ? (
              <Box component='span' sx={visuallyHidden}>
                {order === 'desc' ? 'sorted descending' : 'sorted ascending'}
              </Box>
            ) : null}
          </TableSortLabel>
        </TableCell>
      ))}
      {extra ? <TableCell>{extra}</TableCell> : ''}
    </StyledTableRow>
  )
}

SortTableHead.propTypes = {
  onRequestSort: PropTypes.func.isRequired,
  order: PropTypes.oneOf(['asc', 'desc']).isRequired,
  orderBy: PropTypes.string.isRequired,
}
