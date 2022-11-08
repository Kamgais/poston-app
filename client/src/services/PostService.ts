import { PostDto } from "../types/PostDto";
import axios from 'axios';
import { BASE_URL } from "../api/url";


export class PostService {

    static async getAllPosts():Promise<PostDto|null> {
        try {
            const response = await axios.get(`${BASE_URL}/posts`)
            return response.data;
        } catch (error) {
            console.log(error)
           return null; 
        }
    }


    static async likePost(id:number, likeIndex:number):Promise<string|null> {
        try {
            const response = await axios.get(`${BASE_URL}/posts/like/${id}/${likeIndex}`);
            return response.data;
        } catch (error) {
            console.log(error);
            return null;
        }

    }


    static async unLikePost(id:number, unLikeIndex:number):Promise<string|null> {
        try {
            const response = await axios.get(`${BASE_URL}/posts/unlike/${id}/${unLikeIndex}`);
            return response.data;
        } catch (error) {
          console.log(error);
          return null;  
        }
    }
}




