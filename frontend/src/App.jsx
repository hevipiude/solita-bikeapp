import { useState, useEffect } from "react"
import axios from "axios"
import "./App.css"
import JourneyTable from "./components/JourneyTable"

function App() {
  return (
    <div className="App">
      <h1>tabletest</h1>
      <div>
        <JourneyTable />
      </div>
    </div>
  )
}

export default App
