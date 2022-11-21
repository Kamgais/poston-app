import { NotificationService } from "../../services/NotificationService"
import { NotificationDto } from "../../types/NotificationDto"
import { getAndSetNotifs } from "../notifSlice";




export const getAndSetNotifsByUserId = (userId: number) => async(dispatch:any) => {

    try {
       const response = await NotificationService.getNotifs(userId);
       console.log(response)
       dispatch(getAndSetNotifs(response))
    } catch (error) {
        console.log(error);
        return null;
        
    }
}