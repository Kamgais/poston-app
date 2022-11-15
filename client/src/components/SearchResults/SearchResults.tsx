import React, {Dispatch, FunctionComponent, SetStateAction, useEffect, useState} from 'react'
import { useNavigate } from 'react-router-dom';
import { PostService } from '../../services/PostService';
import { PostDto } from '../../types/PostDto';
import './results.styles/results.css';



type Props = {
    title:string,
    handleVisible: Dispatch<SetStateAction<boolean>>
}

const SearchResults:FunctionComponent<Props> = ({title, handleVisible}) => {
    const [posts,setPosts] = useState<PostDto[]|null>()
    const navigate = useNavigate();
 


    const fetchPosts = async() => {
     const response = await PostService.getAllPosts(title);
     setPosts(response);
     
    }

    const navigateToSinglePost = (id: number) => {
      handleVisible(false)
      navigate(`/posts/${id}`)
    }

    useEffect(() => {
     fetchPosts()
    },[posts])


  return (
    <>
    {  posts?.length !== 0 &&

        <div className='resultContainer'>

        {
            posts?.map((post:any, index) => (
                <div key={index} className="resultElement" onClick={() => navigateToSinglePost(post.id)}>
                <p>{post.title}</p>
            </div>
            ))
        }
        
        
        
      
    </div>

    }

</>
    
  )
}

export default SearchResults
