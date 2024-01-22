import React, {Dispatch, FunctionComponent, useEffect} from 'react';
import {useDispatch, useSelector} from 'react-redux';
import { getAllPosts } from '../../redux/actions/posts.actions';
import { getAndSetNotifsByUserId } from '../../redux/actions/notifs.actions';
import Post from '../../components/Post/Post'
import './posts.styles/posts.css';
import CategoryList from '../../components/CategoryList/CategoryList';


const Posts:FunctionComponent = () => {
    const posts = useSelector((state:any) => state.posts);
    const {user} = useSelector((state:any) => state.auth);
    const dispatch:Dispatch<(dispatch:any) => void> = useDispatch<any>();
    useEffect(() => {
     dispatch(getAllPosts())
     dispatch(getAndSetNotifsByUserId(user.id))
     console.log(posts);
    },[])
 
 
 
 
    return (

    
      <div className='postList'>
        <CategoryList/>
    <div className='postListContainer'>
        
        {
            posts.map((post: any,index: any) => 
                <Post  key={index} post = {post} />
            )
        }
     
   
     
    </div>
    </div>
    
  )
}

export default Posts;
