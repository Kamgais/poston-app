import React,{FunctionComponent, useEffect, useState} from 'react'
import { UserService } from '../../services/UserService'
import { UserDto } from '../../types/UserDto'


type Props = {
    userId: number
}
const ProfileImg:FunctionComponent<Props> = ({userId}) => {
    const [user, setUser] = useState<UserDto|null>();

    const fetchUser = async() => {
        const response = await UserService.getUserById(userId);
        setUser(response);
    }

    useEffect(()=> {
      fetchUser().then((_) => {})
    },[])
  return (
    <img src={user?.profilePic} className="notificationBannerImg"/>
  )
}

export default ProfileImg
