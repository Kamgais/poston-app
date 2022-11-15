import React,{FunctionComponent} from 'react'
import { useSelector, useDispatch } from 'react-redux';
import { signOut } from '../../redux/authSlice';
import { useNavigate } from 'react-router-dom';
import './banner.styles/banner.css';

const Banner:FunctionComponent = () => {
  const navigate = useNavigate();
  const dispatch = useDispatch();
  return (
    <div className='bannerContainer'>
      <div className="bannerMyAccount">
      <i className="fa-solid fa-gear"></i> 
      <p>My account</p>
      </div>
      <div className="bannerCreatePost" onClick={() => navigate('/posts/add')}>
      <i className="fa-solid fa-circle-plus"></i>
      <p>Create post</p>
      </div>

      <div className="bannerMyPost">
      <i className="fa-solid fa-folder-open"></i>
      <p>My posts</p>
      </div>
      <div className="bannerMyPost" onClick={() => dispatch(signOut())}>
      <i className="fa-solid fa-arrow-right-from-bracket"></i>
      <p >Logout</p>
      </div>
    </div>
  )
}

export default Banner
