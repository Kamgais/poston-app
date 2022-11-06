import React, {FunctionComponent} from 'react'
import './navbar.styles/navbar.css';

const Navbar:FunctionComponent = () => {
  return (
    <nav className='navContainer'>
        <div className="navLogo">
            <h1>PostOn</h1>
        </div>
        <div className="navActions">
            <button className="login">Login</button>
            <button className="register">Register</button>
        </div>
      
    </nav>
  )
}

export default Navbar
