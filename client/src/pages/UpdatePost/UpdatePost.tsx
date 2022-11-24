import React,{FunctionComponent, useState, useEffect} from 'react'
import { useLocation, useParams } from 'react-router-dom'
import PostForm from '../../components/PostForm/PostForm'
import { PostService } from '../../services/PostService';
import { PostDto } from '../../types/PostDto';

const UpdatePost: FunctionComponent = () => {
   // const {state} = useLocation();
   const {id} = useParams();
   const [post,setPost] = useState<PostDto|null>();


   const fetchPost = async() => {
     const response = await PostService.getPostById(+id!);
     setPost(response);
   }

   useEffect(() => {
     fetchPost().then((_) => {})
   },[])
    
  return (
    <div>
     <PostForm type='UPDATE' body={post}/> 
    </div>
  )
}

export default UpdatePost;
