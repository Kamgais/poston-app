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
        
            { !logged ?  (
              <>
              <div className="navActions">
               <button className="login" onClick={navigateToLogin} >Login</button>
               <button className="register" onClick={navigateToRegister}>Register</button>
               </div>
               </>
            ) : (
              <>
              <div className="search-bar">
              <input  className='search-bar-input' type="text" />
              <button className='search-bar-button'>Search</button>
              </div>

              <div className="user-infos">
              <i className="fa-solid fa-bell"></i>
                <div className="user-pic">
                  <img src={user.profilePic} alt="" />
                </div>
              </div>
             

              </>
            )
            }
        
      
    </nav>
  )
}

export default Navbar
