import axios from "axios";
import { BASE_URL } from "../api/url";
import { CommentDto } from "../types/CommentDto";



export class CommentService {

    static async saveComment(comment: CommentDto|null): Promise<CommentDto|null> {
        try {
            const response = await axios.post(`${BASE_URL}/comments`, comment)
            return response.data;
        } catch (error) {
            console.log(error);
            return null;
        }
    }


    static async deleteComment(id: number): Promise<String|null> {
        try {
            const response = await axios.delete(`${BASE_URL}/comments/${id}`)
            return response.data;
        } catch (error) {
          console.log(error);
          return null;  
        }
    }

    static async getComments(id:number|undefined): Promise<CommentDto[]|null> {
        try {
          const response = await axios.get(`${BASE_URL}/comments/${id}`);
          return response.data;
        } catch (error) {
         console.log(error);
         return null;   
        }
    }

    static async countComments(id:number): Promise<number|null> {
        try {
           const response = await  axios.get(`${BASE_URL}/comments/count/${id}`)
           return response.data;
        } catch (error) {
          console.log(error);
          return null;  
        }
    }
}