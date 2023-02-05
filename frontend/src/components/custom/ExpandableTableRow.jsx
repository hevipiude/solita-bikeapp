import { Button, IconButton, TableCell } from '@mui/material'
import { useState } from 'react'
import { theme } from '../../themes/theme'
import StyledTableRow from './StyledTableRow'
import InfoIcon from '@mui/icons-material/Info'
import CancelIcon from '@mui/icons-material/Cancel'

const ExpandableTableRow = ({ children, expandComponent }) => {
  const [isExpanded, setIsExpanded] = useState(false)

  return (
    <>
      <StyledTableRow styled={theme}>
        {children}
        <TableCell align='center' padding='checkbox'>
          <Button
            variant='contained'
            onClick={() => setIsExpanded(!isExpanded)}>
            {isExpanded ? <CancelIcon /> : <InfoIcon />}
          </Button>
        </TableCell>
      </StyledTableRow>
      {isExpanded && (
        <StyledTableRow styled={theme}>
          <TableCell colSpan={6}>{expandComponent}</TableCell>
        </StyledTableRow>
      )}
    </>
  )
}

export default ExpandableTableRow
