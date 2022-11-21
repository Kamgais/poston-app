import React, {FunctionComponent, useEffect, Dispatch} from 'react'
import { useDispatch, useSelector } from 'react-redux';
import { getAndSetNotifsByUserId } from '../../redux/actions/notifs.actions';
import NotificationBanner from '../../components/NotificationBanner/NotificationBanner'
import './notifications.styles/notifications.css';

const Notifications: FunctionComponent = () => {
    const dispatch:Dispatch<(dispatch: any) => Promise<null | undefined>> = useDispatch<any>();
    const notifs = useSelector((state:any) => state.notifs)
    const {user} = useSelector((state:any) => state.auth)
    useEffect(() => {
       dispatch(getAndSetNotifsByUserId(user.id))
       console.log(notifs)
    },[])
  return (
    <div className='notificationPageContainer'>

        {
           notifs.map((notification:any, index:any) => (
            <NotificationBanner key={index} notification={notification}/>
           ))
        }
        
        
      
    </div>
  )
}

export default Notifications
