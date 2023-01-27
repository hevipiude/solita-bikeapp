import { useState, useEffect } from 'react'
import axios from "axios";
import './App.css'

function App() {
  const [message, setMessage] = useState({id:0, content:""})
  const url = 'http://localhost:8080/greeting?name=Taateli';

    useEffect(() => {
     axios.get(url).then(response => {
            setMessage(response.data);
        }).catch(error => {
            console.log('error : ');
            console.log(error);
        });
    
  }, []);
       

  return (
    <div className="App">
      <h1>{message.id} {message.content}</h1>
      {console.log(message.id, message.content)}
    </div>
  )
}

export default App
