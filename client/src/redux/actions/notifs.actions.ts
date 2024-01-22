import { NotificationService } from "../../services/NotificationService"
import { getAndSetNotifs, read } from "../notifSlice";




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


export const readNotification = (id: number) => async(dispatch:any) => {
    try {
       const response = await NotificationService.readNotification(id);
       console.log(response);
       dispatch(read(id))
    } catch (error) {
     console.log(error);
     return null;   
    }
}