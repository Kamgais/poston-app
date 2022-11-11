import React,{FunctionComponent} from 'react'
import LikeIcon from '../../assets/icons/like.svg';
import UnLikeIcon from '../../assets/icons/dislike.svg';
import CommentIcon from '../../assets/icons/comment.svg';
import './singlePost.styles/singlePost.css';

const SinglePost:FunctionComponent = () => {
  return (
    <div className='singlePostContainer'>
        <div className="singlePostImage">
         <img src='https://images.pexels.com/photos/6627546/pexels-photo-6627546.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2'/>
        </div>

        <div className="singlePostInfos">
          <h1 className="postTitle">Natur? Drop and Drap...</h1>
          <p className="postContent">
            Lorem ipsum dolor sit amet, consectetur adipisicing elit. 
            Reprehenderit, eos illum. Placeat recusandae iusto, quisquam mollitia 
            porro est odit vero repellat, deleniti asperiores laborum eligendi rem 
            voluptatum voluptatibus magni distinctio exercitationem odio enim, similique 
            ad laudantium consequuntur! Ipsam sit dolore eius mollitia, doloribus rem perferendis 
            necessitatibus, nulla, porro beatae assumenda similique quae quam? Nulla libero alias 
            obcaecati fugiat rerum earum esse dignissimos officia, 
            doloribus amet optio soluta laudantium 
            porro magnam, unde impedit, eius a animi. Et fugiat in 
            praesentium architecto laudantium voluptates
             velit impedit saepe itaque repellat provident totam recusandae, officia 
             tempore placeat voluptas 
             quae aut cum? Itaque, ratione deserunt.
             Lorem ipsum dolor sit amet, consectetur adipisicing elit. 
            Reprehenderit, eos illum. Placeat recusandae iusto, quisquam mollitia 
            porro est odit vero repellat, deleniti asperiores laborum eligendi rem 
            voluptatum voluptatibus magni distinctio exercitationem odio enim, similique 
            ad laudantium consequuntur! Ipsam sit dolore eius mollitia, doloribus rem perferendis 
            necessitatibus, nulla, porro beatae assumenda similique quae quam? Nulla libero alias 
            obcaecati fugiat rerum earum esse dignissimos officia, 
            doloribus amet optio soluta laudantium 
            porro magnam, unde impedit, eius a animi. Et fugiat in 
            praesentium architecto laudantium voluptates
             velit impedit saepe itaque repellat provident totam recusandae, officia 
             tempore placeat voluptas 
             quae aut cum? Itaque, ratione deserunt.
             Lorem ipsum dolor sit amet, consectetur adipisicing elit. 
            Reprehenderit, eos illum. Placeat recusandae iusto, quisquam mollitia 
            porro est odit vero repellat, deleniti asperiores laborum eligendi rem 
            voluptatum voluptatibus magni distinctio exercitationem odio enim, similique 
            ad laudantium consequuntur! Ipsam sit dolore eius mollitia, doloribus rem perferendis 
            necessitatibus, nulla, porro beatae assumenda similique quae quam? Nulla libero alias 
            obcaecati fugiat rerum earum esse dignissimos officia, 
            doloribus amet optio soluta laudantium 
            porro magnam, unde impedit, eius a animi. Et fugiat in 
            praesentium architecto laudantium voluptates
             velit impedit saepe itaque repellat provident totam recusandae, officia 
             tempore placeat voluptas 
             quae aut cum? Itaque, ratione deserunt.
             Lorem ipsum dolor sit amet, consectetur adipisicing elit. 
            Reprehenderit, eos illum. Placeat recusandae iusto, quisquam mollitia 
            porro est odit vero repellat, deleniti asperiores laborum eligendi rem 
            voluptatum voluptatibus magni distinctio exercitationem odio enim, similique 
            ad laudantium consequuntur! Ipsam sit dolore eius mollitia, doloribus rem perferendis 
            necessitatibus, nulla, porro beatae assumenda similique quae quam? Nulla libero alias 
            obcaecati fugiat rerum earum esse dignissimos officia, 
            doloribus amet optio soluta laudantium 
            porro magnam, unde impedit, eius a animi. Et fugiat in 
            praesentium architecto laudantium voluptates
             velit impedit saepe itaque repellat provident totam recusandae, officia 
             tempore placeat voluptas 
             quae aut cum? Itaque, ratione deserunt.
             Lorem ipsum dolor sit amet, consectetur adipisicing elit. 
            Reprehenderit, eos illum. Placeat recusandae iusto, quisquam mollitia 
            porro est odit vero repellat, deleniti asperiores laborum eligendi rem 
            voluptatum voluptatibus magni distinctio exercitationem odio enim, similique 
            ad laudantium consequuntur! Ipsam sit dolore eius mollitia, doloribus rem perferendis 
            necessitatibus, nulla, porro beatae assumenda similique quae quam? Nulla libero alias 
            obcaecati fugiat rerum earum esse dignissimos officia, 
            doloribus amet optio soluta laudantium 
            porro magnam, unde impedit, eius a animi. Et fugiat in 
            praesentium architecto laudantium voluptates
             velit impedit saepe itaque repellat provident totam recusandae, officia 
             tempore placeat voluptas 
             quae aut cum? Itaque, ratione deserunt.
          </p>

          <div className="postActions">
            <div className="like">
            <img src={LikeIcon} alt="" />
            <p>30</p>
            </div>
           <div className="unlike">
           <img src={UnLikeIcon} alt="" />
           <p>50</p>
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
