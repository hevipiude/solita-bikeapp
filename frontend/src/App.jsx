import { BrowserRouter as Router, Route, Routes } from 'react-router-dom'
import JourneyTable from './components/JourneyTable'
import NavBar from './components/NavBar'
import StationDetails from './components/StationDetails'
import StationTable from './components/StationTable'

function App() {
  return (
    <div className='App'>
      <Router>
        <NavBar />
        <div>
          <Routes>
            <Route path='/journeys' element={<JourneyTable />} />
            <Route path='/stations' element={<StationTable />} />
          </Routes>
        </div>
      </Router>
    </div>
  )
}

export default App
