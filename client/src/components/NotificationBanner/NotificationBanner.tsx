import React,{FunctionComponent, Dispatch} from 'react'
import { useDispatch, useSelector } from 'react-redux';
import { readNotification } from '../../redux/actions/notifs.actions';
import { useNavigate } from 'react-router-dom';
import { NotificationDto } from '../../types/NotificationDto';
import ProfileImg from '../ProfileImg/ProfileImg';
import './notificationBanner.styles/notificationBanner.css';


type Props = {
    notification: NotificationDto
}
const NotificationBanner: FunctionComponent<Props> = ({notification}) => {

  const navigate = useNavigate();
  const dispatch: Dispatch<(dispatch: any) => Promise<null | undefined>> = useDispatch<any>();
  const {user} = useSelector((state:any) => state.auth)


  const handleNavigate = () => {
    dispatch(readNotification(notification.id!));
   navigate(`/posts/${notification.post?.id}`)
  }
  return (
    <>
   { notification.userId !== user.id &&  ( <> <div className='notificationBanner' onClick={handleNavigate}>
      <ProfileImg userId={notification.userId!}/>
      <p className="notificationBannerMessage">{notification.message}</p>
    </div>
    </>
   )
  }
  </>
  )
}

export default NotificationBanner
