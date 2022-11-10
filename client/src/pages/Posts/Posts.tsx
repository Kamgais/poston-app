import React, {Dispatch, FunctionComponent, useEffect} from 'react';
import {useDispatch, useSelector} from 'react-redux';

import { getAllPosts } from '../../redux/actions/posts.actions';
import Post from '../../components/Post/Post'
import './posts.styles/posts.css';
import CategoryList from '../../components/CategoryList/CategoryList';


const Posts:FunctionComponent = () => {
    const posts = useSelector((state:any) => state.posts);
    const dispatch:Dispatch<(dispatch:any) => void> = useDispatch<any>();
    useEffect(() => {
     dispatch(getAllPosts())
     console.log(posts)
    },[])
  return (

    
      <div className='postList'>
        <CategoryList/>
    <div className='postListContainer'>
        
        { posts.length !== 0 &&
            posts.map((post: any,index: any) => 
                <Post  key={index} post = {post} />
            )
        }
     
   
     
    </div>
    </div>
    
  )
}

export default Posts;
