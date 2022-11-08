import React, {FunctionComponent} from 'react'
import {useSelector} from 'react-redux';
import {useNavigate} from 'react-router-dom';
import './navbar.styles/navbar.css';

const Navbar:FunctionComponent = () => {
  const navigate = useNavigate();
  const {user, logged} = useSelector((state:any) => state.auth)
 

  const navigateToLogin = () => {
    navigate('/login')
    window.location.reload();
  }

  const navigateToRegister = () => {
    navigate('/signup')
    window.location.reload();
  }
  return (
    <nav className='navContainer'>
        <div className="navLogo">
            <h1>PostOn</h1>
        </div>
        <div className="navActions">
            { !logged ?  (
              <>
               <button className="login" onClick={navigateToLogin} >Login</button>
               <button className="register" onClick={navigateToRegister}>Register</button>
               </>
            ) : 
            <p style={{fontSize: '20px', fontWeight: 'bold', fontFamily: 'Roboto'}}>{user.username}</p>}
        </div>
      
    </nav>
  )
}

export default Navbar
