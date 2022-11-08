import axios from "axios";
import { BASE_URL } from "../api/url";
import { UserDto } from "../types/UserDto";

export class AuthService {


    static async saveUser(user:UserDto|null):Promise<UserDto|void> {
        try {
            const response = await axios.post(`${BASE_URL}/auth/register`, user);
            return response.data;
        } catch (error) {
           console.log(error);
         
        }
    }


    static async loginUser(user:UserDto):Promise<UserDto|void> {
        try {
           const response = await axios.post(`${BASE_URL}/auth/login`, user);
           return response.data;
        } catch (error) {
            console.log(error);
            
        }
    }
}