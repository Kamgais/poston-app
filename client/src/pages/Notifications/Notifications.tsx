import React, {FunctionComponent, useEffect, Dispatch} from 'react'
import { useDispatch, useSelector } from 'react-redux';
import { getAndSetNotifsByUserId } from '../../redux/actions/notifs.actions';
import NotificationBanner from '../../components/NotificationBanner/NotificationBanner'
import './notifications.styles/notifications.css';
import { notificationContext } from '../../context/NotificationContext';

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

        { notifs.filter((e:any) => !e.read && e.userId !== user.id).length !== 0 ?
           notifs.filter((e:any) => !e.read).map((notification:any, index:any) => (
            <NotificationBanner key={index} notification={notification}/>
           ))  : 
           <h1 style={{fontFamily: 'Roboto', textAlign: 'center', fontSize: '80px' }}><i className="fa-solid fa-comment-slash"></i>No Notification</h1>
        }
        
        
      
    </div>
  )
}

export default Notifications
