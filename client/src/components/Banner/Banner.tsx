import React,{FunctionComponent} from 'react'
import './banner.styles/banner.css';

const Banner:FunctionComponent = () => {
  return (
    <div className='bannerContainer'>
      <div className="bannerMyAccount">
      <i className="fa-solid fa-gear"></i> 
      <p>My account</p>
      </div>
      <div className="bannerCreatePost">
      <i className="fa-solid fa-circle-plus"></i>
      <p>Create post</p>
      </div>

      <div className="bannerMyPost">
      <i className="fa-solid fa-folder-open"></i>
      <p>My posts</p>
      </div>
    </div>
  )
}

export default Banner
