import { useState } from 'react'
import Box from '@mui/material/Box'
import Tabs from '@mui/material/Tabs'
import Tab from '@mui/material/Tab'
import { useLocation, useNavigate } from 'react-router-dom'
import { AppBar, Toolbar, Typography } from '@mui/material'

const tabProps = [
  { label: 'Matkat', route: '/' },
  { label: 'Asemat', route: '/stations' },
]

export default function NavBar() {
  const location = useLocation()
  const navigate = useNavigate()

  const [value, setValue] = useState(location.pathname)

  const onChange = (_, newValue) => {
    setValue(newValue)
    navigate(newValue)
  }

  return (
    <Box sx={{ width: '100%' }}>
      <Box sx={{ flexGrow: 1 }}>
        <AppBar position='static'>
          <Toolbar>
            <Typography variant='h5' component='div' sx={{ flexGrow: 1 }}>
              HELSINKI CITYBIKE
            </Typography>
          </Toolbar>
        </AppBar>
      </Box>
      <Tabs value={value} onChange={onChange}>
        {tabProps.map(({ route, label }) => (
          <Tab key={route} label={label} value={route} />
        ))}
      </Tabs>
    </Box>
  )
}
