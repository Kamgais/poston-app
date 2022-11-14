import React,{FunctionComponent, useEffect, useState} from 'react'
import { useSelector } from 'react-redux';
import { CommentService } from '../../services/CommentService'
import { CommentDto } from '../../types/CommentDto'
import './comments.styles/comments.css';


type Props = {
    comments?: CommentDto[]|null,
    delete: (id: number) => Promise<void>
}
const Comments:FunctionComponent<Props> = (props) => {
    const {user} = useSelector((state:any) => state.auth)
    

    

   



    useEffect(() => {
    //  fetchComments();
    },[])
  return (
    <>

    {
        props.comments?.map((comment:any, index) => (
            
            <div className="comment"  key={index}>
            <div className="commentAutor">
            <img src={comment.user.profilePic} />
            </div>
            <div className="commentContent">
            {comment.message}
            </div>
            { comment.user.id === user.id && <div onClick={() => props.delete(comment.id)} className="commentAction">
            <i className="fa-solid fa-trash"></i>
            </div>}
         </div>
        ))
    }
     
      
    </>
  )
}

export default Comments
