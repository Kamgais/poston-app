import React,{FunctionComponent, useEffect, useState, useContext, useRef, Dispatch} from 'react'
import { useSelector, useDispatch } from 'react-redux';
import { likePost, unLikePost } from '../../redux/actions/posts.actions';
import { useNavigate } from 'react-router-dom';
import {notificationContext} from '../../context/NotificationContext';
import { useParams } from 'react-router-dom';
import LikeIcon from '../../assets/icons/like.svg';
import UnLikeIcon from '../../assets/icons/dislike.svg';
import CommentIcon from '../../assets/icons/comment.svg';
import './singlePost.styles/singlePost.css';
import { PostDto } from '../../types/PostDto';
import { PostService } from '../../services/PostService';
import Comments from '../../components/Comments/Comments';
import { CommentService } from '../../services/CommentService';
import { CommentDto } from '../../types/CommentDto';

const SinglePost:FunctionComponent = () => {
    const {id} = useParams();
    const commentRef = useRef<HTMLInputElement|null>(null);
    const dispatch:Dispatch<(dispatch: any) => Promise<null | undefined>> = useDispatch<any>();
    const navigate = useNavigate();
    const {user} = useSelector((state:any) => state.auth)
    const posts = useSelector((state:any) => state.posts);
    const {handleNotification} = useContext(notificationContext);
    const [post,setPost] = useState<PostDto|null>();
    const [commentCount, setCommentCount] = useState<number|null>(0);
    const [comment,setComment] = useState<CommentDto|null>({
      message: '',
      post: post,
      user: user
    });
    const [comments, setComments] = useState<CommentDto[]|null>(null);
    const [status,setStatus] = useState<string>("");
    


    const fetchPost = async() => {
      if(id) {
        const response = await PostService.getPostById(+id!)
        setPost(response);
      }
        
    }

    const fetchComments = async() => {
       const response = await CommentService.getComments(+id!);
        setComments(response)
      
      
       
      
      
  }




    const handleComment = (e: React.ChangeEvent<HTMLButtonElement>) => {
     
        setComment({...comment, message: e.target.value, post: post})
        console.log(comment)
      
    }


    const saveComment = async() => {
      if(comment?.message !== '' && comment) {
        const response =  await CommentService.saveComment(comment!);
        commentRef.current!.value = '';
        handleNotification('success', 'successful commented');
       console.log(response)
          // @ts-ignore
          setComments(function (prevState: CommentDto[] | null) {
              return [...prevState!, response];
          });
      // navigate(0)
      }
     
    }


    const deleteComment = async(id: number) => {
      const response = await CommentService.deleteComment(id);
      handleNotification('success', 'successful deleted');
    
      
     }



     const addLike = () => {
      if(status === 'liked') {
          dispatch(likePost(+id!, -1))  
          setStatus('') 
          
      } else  if (status === 'unliked'){
          dispatch(unLikePost(+id!, -1));
          dispatch(likePost(+id!, 1))
          setStatus('liked');
          

      } else {
          dispatch(likePost(+id!, 1))
          setStatus('liked'); 
          
      }
     
  }


  const disLike = () => {
    if(status === 'liked') {
        dispatch(likePost(+id!, -1))
        dispatch(unLikePost(+id!, 1));
        setStatus('unliked')
        
       
    } else if(status === 'unliked') {
    dispatch(unLikePost(+id!, -1));
    setStatus('');
    
   
    }  else {
        dispatch(unLikePost(+id!, 1))
       setStatus('unliked')
        
       
    }
   
}

    const countComments = async() => {
      if(id) {
        const response = await CommentService.countComments(Number(id!))
        setCommentCount(response);
      }
      
    }
   
    useEffect(() => {
     fetchPost().then(r => {});
    fetchComments().then(r => {} );
     countComments().then(r => {} )
     
     
    
    },[])
  

    useEffect(() => {
      console.log(status)
    },[status])

  return (
    <div className='singlePostContainer'>
        <div className="singlePostImage">
         <img src={`data:image/jpeg;base64,${post?.image?.picByte}`}/>
         <div  style={{display: 'flex', justifyContent: 'space-between'}} className="author-infos">
         <p style={{fontFamily: 'Roboto'}}>Author : { post  && post?.user?.username?.toUpperCase()}</p>
         <div style={{display: 'flex' , gap: '20px', fontFamily: 'Roboto'}} className="categories">
          {
            post?.categories?.map((category:any, index) => (
              <p  style={{background: 'rgb(4, 179, 170)', width: '100px' , display: 'flex', justifyContent: 'center', color: '#fff', height: '30px', alignItems: 'center' }} key={index}>{category.categoryName}</p>
            ))
          }
         </div>
         </div>
         
        </div>

        <div className="singlePostInfos">
          <h1 className="postTitle">{post?.title}</h1>
          <p className="postContent">
            {post?.content}
          </p>

          <div className="postActions">
            <div   className="like">
            <img  src={LikeIcon} onClick={addLike}  alt="" />
            <p>{post?.likeCount}</p>
            </div>
           <div  className="unlike">
           <img   src={UnLikeIcon} onClick={disLike} alt="" />
           <p>{post?.unlikeCount}</p>
           </div>
           <div className="comment">
           <img src={CommentIcon} alt="" />
           <p>{commentCount}</p>
           </div>
          
          </div>
        </div>

        <div className="singlePostComments">
         <input type="text" ref={commentRef} onChange={(e:any) => handleComment(e)} />
         <button  onClick={saveComment}>Publish</button>
        <div className="comments">
        { comments && <Comments comments={comments} delete={deleteComment}/>}
        </div>
        
        
         </div>
      
    </div>
  )
}

export default SinglePost
