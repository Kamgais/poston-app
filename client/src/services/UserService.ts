import axios from "axios";
import { BASE_URL } from "../api/url";
import { UserDto } from "../types/UserDto";



export class UserService {


    static async getUserByUsername(name: string):Promise<UserDto[]|null> {

        try {
          const response = await axios.get(`${BASE_URL}/users?name=${name}`);
          return response.data; 
        } catch (error) {
           console.log(error);
           return null; 
        }
    }
}