import axios from "axios";
import { BASE_URL } from "../api/url";
import { TagDto } from "../types/TagDto";


export class TagService {

    static async createTags(tags: TagDto[]):Promise<TagDto[]|null> {
        try {
           const response = await axios.post(`${BASE_URL}/tags/all`, tags);
           return response.data;
        } catch (error) {
          console.log(error);
          return null;  
        }
    }

    static async getTagsByPostId(postId: number) : Promise<TagDto[]|null> {
      try {
        const response = await axios.get(`${BASE_URL}/tags/${postId}`);
        return response.data;
      } catch (error) {
        console.log(error);
        return null;
      }
    }


    static async updateTags(tags: TagDto[], id: number) : Promise<TagDto[]|null> {
      try {
        const response = await axios.put(`${BASE_URL}/tags/${id}`, tags);
        return response.data;
      } catch (error) {
        console.log(error);
        return null;
      }
    }
}