import React, {Dispatch, FunctionComponent, useEffect} from 'react';
import {useDispatch, useSelector} from 'react-redux';

import { getAllPosts } from '../../redux/actions/posts.actions';
import Post from '../../components/Post/Post'
import './posts.styles/posts.css';


const Posts:FunctionComponent = () => {
    const posts = useSelector((state:any) => state.posts);
    const dispatch:Dispatch<(dispatch:any) => void> = useDispatch<any>();
    useEffect(() => {
     dispatch(getAllPosts())
     console.log(posts)
    },[posts])
  return (
    <div className='postListContainer'>
        {
            posts.map((post: any,index: any) => 
                <Post  key={index} post = {post} />
            )
        }
     
   
     
    </div>
  )
}

export default Posts
