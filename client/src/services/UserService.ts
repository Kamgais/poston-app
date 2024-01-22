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


    static async getUserById(id: number): Promise<UserDto|null> {
     
      try {
        const response = await axios(`${BASE_URL}/users/${id}`);
        return response.data;
      } catch (error) {
        console.log(error);
        return null;
      }
    }



    static async updateUserAccount(id:number, user:UserDto) : Promise<UserDto|null> {
      try {
        const response = await axios.put(`${BASE_URL}/users/${id}`, user);
        return response.data;
      } catch (error) {
        console.log(error);
        return null;
      }
    }


    static async deleteUserAccount(id: number): Promise<string|null> {
     try {
      const response = await axios.delete(`${BASE_URL}/users/${id}`);
      return response.data;
     } catch (error) {
      console.log(error);
      return null;
     }
    }
}