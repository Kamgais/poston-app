import React,{FunctionComponent} from 'react'
import { NotificationDto } from '../../types/NotificationDto';
import ProfileImg from '../ProfileImg/ProfileImg';
import './notificationBanner.styles/notificationBanner.css';


type Props = {
    notification: NotificationDto
}
const NotificationBanner: FunctionComponent<Props> = ({notification}) => {
  return (
    <div className='notificationBanner'>
      <ProfileImg userId={notification.userId!}/>
      <p className="notificationBannerMessage">{notification.message}</p>
    </div>
  )
}

export default NotificationBanner
