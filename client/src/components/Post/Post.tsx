import React,{FunctionComponent, Dispatch, useState, useEffect} from 'react'
import { useNavigate } from 'react-router-dom';
import {useDispatch, useSelector} from 'react-redux';
import { likePost,unLikePost } from '../../redux/actions/posts.actions';
import LikeIcon from '../../assets/icons/like.svg';
import UnLikeIcon from '../../assets/icons/dislike.svg';
import CommentIcon from '../../assets/icons/comment.svg';
import './post.styles/post.css';
import { PostDto } from '../../types/PostDto';
import { CommentService } from '../../services/CommentService';

type Props = {
    post:PostDto
}

const Post:FunctionComponent<Props> = ({post}) => {
    const dispatch: Dispatch<(dispatch: any) => Promise<null | undefined>> = useDispatch<any>();
    const [status, setStatus] = useState<string>("");
    const [commentCount, setCommentCount] = useState<number|null>(0);
    const navigate = useNavigate();
   



    const countComments = async() => {
        const response = await CommentService.countComments(post.id!);
        setCommentCount(response);
    }


    useEffect(() => {
      countComments()
    },[])

   


    const addLike = () => {
        if(status === 'liked') {
            dispatch(likePost(post.id!, -1))  
            setStatus('') 
        } else  if (status === 'unliked'){
            dispatch(unLikePost(post.id!, -1));
            dispatch(likePost(post.id!, 1))
            setStatus('liked');

        } else {
            dispatch(likePost(post.id!, 1))
            setStatus('liked');  
        }
       
    }

    const disLike = () => {
        if(status === 'liked') {
            dispatch(likePost(post.id!, -1))
            dispatch(unLikePost(post.id!, 1));
            setStatus('unliked')
            console.log('hey')
        } else if(status === 'unliked') {
        dispatch(unLikePost(post.id!, -1));
        setStatus('');
        }  else {
            dispatch(unLikePost(post.id!, 1))
            setStatus('unliked')
        }
       
    }
  return (
    <div className='postContainer'>
        <div className="categoriesLabels">
            {post.categories?.map((category) => (
                <div >{category.categoryName}</div>
            ))}
        </div>
      <img  onClick={() => navigate(`/posts/${post.id}`)} src={`data:image/jpg;base64,${post?.image?.picByte}`} alt="" />
      <div className="postInfosContainer">
        <div className="postTitle">
            <h3>{post.title}</h3>
        </div>
        <div className="postNumerics">
            <div className="likes">
            <img src={LikeIcon}  onClick={addLike} alt="" />
            <p  >{post.likeCount}</p>
            </div>
            <div className="unlikes">
            <img src={UnLikeIcon}  onClick={disLike} alt="" />
            <p>{post.unlikeCount}</p>
            </div>
            <div className="comments">
            <img src={CommentIcon} alt="" />
            <p>{commentCount}</p>
            </div>
        </div>
      </div>
    </div>
  )
}

export default Post
