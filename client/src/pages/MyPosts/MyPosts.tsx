import React, {FunctionComponent, useState, useEffect}from 'react'
import { useSelector } from 'react-redux';
import Post from '../../components/Post/Post'
import { PostService } from '../../services/PostService';
import { PostDto } from '../../types/PostDto';

const MyPosts:FunctionComponent = () => {
    const [posts,setPosts] = useState<PostDto[]|null>();
    const {user} = useSelector((state:any) => state.auth);

    const fetchPosts = async() => {
        const response = await PostService.getPostByUserId(user.id);
        setPosts(response);
    }

   useEffect(() => {
     fetchPosts()
         .then((_) => {})
         .catch((err)=> console.log(err))
   },[])
  return (
    <div className='postList'>
        <h1 style={{fontFamily: 'Roboto'}}>My Posts</h1>
    <div className='postListContainer'>
        
        { posts &&
            posts.map((post: any,index: any) => 
                <Post  key={index} post = {post} />
            )
        }
     
   
     
    </div>
    </div>
  )
}

export default MyPosts
