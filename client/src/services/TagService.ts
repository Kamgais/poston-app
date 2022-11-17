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
}