import { PostService } from "../../services/PostService"
import {getPosts, addLike, addDislike} from '../postSlice';


export const getAllPosts = () => async(dispatch:any) => {
    try {
        const response = await PostService.getAllPosts();
        dispatch(getPosts(response));
    } catch (error) {
        console.log(error)
       return null; 
    }
}


export const likePost = (id:number, likeIndex:number) => async(dispatch:any) => {
    try {
        const response = await PostService.likePost(id, likeIndex);
        dispatch(addLike({id, likeCounter: likeIndex}))
    } catch (error) {
        console.log(error);
        return null;
    }
}

export const unLikePost = (id:number, unLikeIndex:number) => async(dispatch:any) => {
    try {
        const response = await PostService.likePost(id, unLikeIndex);
        dispatch(addDislike({id, unLikeCounter: unLikeIndex}))
    } catch (error) {
      console.log(error);
      return null;  
    }
}