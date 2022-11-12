import React,{FunctionComponent, useEffect, useState} from 'react'
import { CommentService } from '../../services/CommentService'
import { CommentDto } from '../../types/CommentDto'


type Props = {
    id:number
}
const Comments:FunctionComponent<Props> = ({id}) => {
    const [comments,setComments] = useState<CommentDto[]|null>()

    const fetchComments = async() => {
        const response = await CommentService.getComments(id);
        setComments(response);
    }
    useEffect(() => {
      fetchComments();
    },[id])
  return (
    <>

    {
        comments?.map((comment:any) => (
            
            <div className="comment">
            <div className="commentAutor">
            <img src="https://images.pexels.com/photos/415829/pexels-photo-415829.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2" alt="" />
            </div>
            <div className="commentContent">
           Lorem ipsum dolor sit amet, consectetur adipisicing elit. 
           Ipsam reprehenderit iusto, a quam maiores consequatur voluptas officia v
           ero voluptates temporibus, quod quis! Dolor harum labore corporis? Assumenda atque consectetur velit!
            </div>
         </div>
        ))
    }
     
      
    </>
  )
}

export default Comments
