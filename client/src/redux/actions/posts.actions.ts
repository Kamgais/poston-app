import { PostService } from "../../services/PostService"
import {getPosts, addLike, addDislike} from '../postSlice';


export const getAllPosts = () => async(dispatch:any) => {
    try {
        const response = await PostService.getAllPosts();
        console.log(response)
        dispatch(getPosts(response));
    } catch (error) {
        console.error(error)
       return null; 
    }
}


export const getPostByCategoryName = (categoryName: String) => async(dispatch:any) => {
    try {
        const response = await PostService.getPostByCategoryName(categoryName);
        dispatch(getPosts(response))
    } catch (error) {
        console.error(error);
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
        const response = await PostService.unLikePost(id, unLikeIndex);
        dispatch(addDislike({id, unLikeCounter: unLikeIndex}))
    } catch (error) {
      console.log(error);
      return null;  
    }
}