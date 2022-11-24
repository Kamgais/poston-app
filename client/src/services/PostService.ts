import { PostDto } from "../types/PostDto";
import axios from 'axios';
import { BASE_URL } from "../api/url";


export class PostService {

    static async getAllPosts(title?:string):Promise<PostDto[]|null> {
        try {
            let response;
            if(title) {
                response = await axios.get(`${BASE_URL}/posts?title=${title}`)
            } else {
                response = await axios.get(`${BASE_URL}/posts`)
            }
           
            return response.data;
        } catch (error) {
            console.log(error)
           return null; 
        }
    }


    static async savePost(post: PostDto|null): Promise<PostDto|null> {
        try {
            const response = await axios.post(`${BASE_URL}/posts`, post)
            return response.data;
        } catch (error) {
         console.log(error);
         return null;   
        }
    }


    static async updatePost(post: PostDto, id: number): Promise<PostDto|null> {
        try {
          const response = await axios.put(`${BASE_URL}/posts/${id}`, post);
          return response.data;  
        } catch (error) {
         console.log(error);
         return null;   
        }
    }



    static async getPostById(id:number):Promise<PostDto|null> {
        try {
           const response = await axios.get(`${BASE_URL}/posts/${id}`);
           return response.data;
        } catch (error) {
            console.log(error);
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

    static async getPostByCategoryName(categoryName: String):Promise<PostDto[]|null> {
        try {
            const response = await axios.get(`${BASE_URL}/posts/categories?categoryName=${categoryName}`);
            return response.data;
        } catch (error) {
          console.log(error);
          return null; 
        }
    }


    static async deletePost(id: number) : Promise<string|null> {
        try {
            const response = await axios.delete(`${BASE_URL}/posts/${id}`);
            return response.data;
        } catch (error) {
           console.log(error) 
           return null;
        }
    }
}




