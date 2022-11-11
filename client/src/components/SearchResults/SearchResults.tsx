import React, {FunctionComponent, useEffect, useState} from 'react'
import { PostService } from '../../services/PostService';
import { PostDto } from '../../types/PostDto';
import './results.styles/results.css';



type Props = {
    title:string
}

const SearchResults:FunctionComponent<Props> = ({title}) => {
    const [posts,setPosts] = useState<PostDto[]|null>()
 


    const fetchPosts = async() => {
     const response = await PostService.getAllPosts(title);
     setPosts(response);
     
    }

    useEffect(() => {
     fetchPosts()
    },[posts])


  return (
    <>
    {  posts?.length !== 0 &&

        <div className='resultContainer'>

        {
            posts?.map((post:any) => (
                <div className="resultElement">
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
