import React from 'react';
import './App.css';
import Navbar from './components/Navbar/Navbar';
import LoginPage from './pages/LoginPage';
import RegisterPage from './pages/RegisterPage';

function App() {
  return (
    <>
    <header className="App">
     <Navbar/>
    </header>
    <main>
      <RegisterPage/>
    </main>
    </>
  );
}

export default App;
