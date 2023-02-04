import { BrowserRouter as Router, Route, Routes } from 'react-router-dom'
import JourneyTable from './components/JourneyTable'
import NavBar from './components/NavBar'
import StationTable from './components/StationTable'
import { ThemeProvider } from '@mui/material'
import { theme } from './themes/theme'

function App() {
  return (
    <ThemeProvider theme={theme}>
      <div className='App'>
        <Router>
          <NavBar />
          <div>
            <Routes>
              <Route path='/' element={<JourneyTable />} />
              <Route path='/stations' element={<StationTable />} />
            </Routes>
          </div>
        </Router>
      </div>
    </ThemeProvider>
  )
}

export default App
