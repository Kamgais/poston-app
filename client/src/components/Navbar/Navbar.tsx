import React, {FunctionComponent, useState} from 'react'
import {useSelector} from 'react-redux';
import {useNavigate} from 'react-router-dom';
import Banner from '../Banner/Banner';
import SearchResults from '../SearchResults/SearchResults';
import './navbar.styles/navbar.css';

const Navbar:FunctionComponent = () => {
  const navigate = useNavigate();
  const {user, logged} = useSelector((state:any) => state.auth)
  const notifs = useSelector((state:any) => state.notifs);
  const [inputValue,setInputValue] = useState<string>("");
  const [visible, setVisible] = useState<boolean>(false);
  const [bannerOpened, setBannerOpened] = useState<boolean>(false);
  const imageUrl = "https://icon-library.com/images/none-icon/none-icon-0.jpg";


  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    if(e.target.value !== '') {
      setInputValue(e.target.value);
      setVisible(true)
    } else {
      setVisible(false)
    }
    

  }


  const handleBanner = () => {
    setBannerOpened(!bannerOpened)
  }
 

  const navigateToLogin = () => {
    navigate('/login')
    window.location.reload();
  }

  const navigateToRegister = () => {
    navigate('/signup')
    window.location.reload();
  }

  const navigateToNotificationPage = () => {
    navigate('/notifs');
  }
  return (
    <nav className='navContainer'>
        <div className="navLogo">
            <h1 onClick={() => navigate('/posts')}>PostOn</h1>
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
              <input  className='search-bar-input' type="text" onChange={(e) => handleChange(e)} />
              { visible && <SearchResults  title={inputValue.toLowerCase() } handleVisible = {setVisible}/>}
              
              </div>

              <div className="user-infos">
              <i className="fa-solid fa-bell" onClick={navigateToNotificationPage} ></i>
               { notifs.filter((e:any) => !e.read && e.userId !== user.id ).length !== 0 && <div className="notifs-quote">
                {notifs.filter((e:any) => !e.read && e.userId !== user.id ).length}
               </div>}
                <div onClick={handleBanner} className="user-pic">
                  <img  src={user.image ? `data:image/jpeg;base64,${user?.image?.picByte}` : imageUrl} alt="" />
                  {bannerOpened && <Banner/>}
                </div>
                <p style={{fontSize: '20px', fontFamily: 'Roboto'}}>{user.username}</p>
              </div>
             

              </>
            )
            }
        
      
    </nav>
  )
}

export default Navbar
