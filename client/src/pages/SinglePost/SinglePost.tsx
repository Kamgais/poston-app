import React,{FunctionComponent, useEffect, useState} from 'react'
import { useParams } from 'react-router-dom';
import LikeIcon from '../../assets/icons/like.svg';
import UnLikeIcon from '../../assets/icons/dislike.svg';
import CommentIcon from '../../assets/icons/comment.svg';
import './singlePost.styles/singlePost.css';
import { PostDto } from '../../types/PostDto';
import { PostService } from '../../services/PostService';

const SinglePost:FunctionComponent = () => {
    const {id} = useParams();
    const [post,setPost] = useState<PostDto|null>();


    const fetchPost = async() => {
        const response = await PostService.getPostById(+id!)
        setPost(response);
    }
   
    useEffect(() => {
     fetchPost();
    },[])

  return (
    <div className='singlePostContainer'>
        <div className="singlePostImage">
         <img src={post?.postImage}/>
        </div>

        <div className="singlePostInfos">
          <h1 className="postTitle">{post?.title}</h1>
          <p className="postContent">
            {post?.content}
          </p>

          <div className="postActions">
            <div className="like">
            <img src={LikeIcon} alt="" />
            <p>{post?.likeCount}</p>
            </div>
           <div className="unlike">
           <img src={UnLikeIcon} alt="" />
           <p>{post?.unlikeCount}</p>
           </div>
           <div className="comment">
           <img src={CommentIcon} alt="" />
           <p>30</p>
           </div>
          
          </div>
        </div>

        <div className="singlePostComments">
         <input type="text" />
         <button>Publish</button>
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
        </div>
      
    </div>
  )
}

export default SinglePost
