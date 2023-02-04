import { createTheme } from '@mui/material/styles'

export const theme = createTheme({
  palette: {
    type: 'light',
    primary: {
      main: '#284c49',
    },
    secondary: {
      main: '#e57373',
    },
  },
  shape: {
    borderRadius: 4,
  },
  spacing: 8,
  overrides: {
    MuiAppBar: {
      colorInherit: {
        backgroundColor: '#002422',
        color: '#fff',
      },
    },
  },
  props: {
    MuiAppBar: {
      color: 'inherit',
    },
  },
})
