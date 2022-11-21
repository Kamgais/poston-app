import axios from "axios";
import { BASE_URL } from "../api/url";
import { NotificationDto } from "../types/NotificationDto";

export class NotificationService {


    static async createNotif(notification: NotificationDto): Promise<NotificationDto|null> {
        try {
            const response = await axios.post(`${BASE_URL}/notifications`, notification);
            return response.data;
        } catch (error) {
          console.log(error);
          return null;  
        }
    }


    static async getNotifs(userId: number): Promise<NotificationDto[]|null> {
        try {
           const response =  await axios.get(`${BASE_URL}/notifications/${userId}`)
           return response.data;
        } catch (error) {
          console.log(error);
          return null;  
        }
    }
}