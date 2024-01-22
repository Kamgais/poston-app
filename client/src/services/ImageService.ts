import axios from "axios";
import { BASE_URL } from "../api/url";
import { ImageDto } from "../types/ImageDto";

export class ImageService {


    static async uploadImage(data: FormData):Promise<string|null> {
        try {
            const response = await axios.post(`${BASE_URL}/storage`,data);
            return response.data;
        } catch (error) {
          console.log(error);
          return null;  
        }
    }


    static async getImage(name: string):Promise<ImageDto|null> {
        try {
          const response = await axios.get(`${BASE_URL}/images/${name}`);
          return response.data;  
        } catch (error) {
         console.log(error);
         return null;   
        }
    }
}