import React,{FunctionComponent, useEffect, useState} from 'react'
import { UserService } from '../../services/UserService'
import { UserDto } from '../../types/UserDto'


type Props = {
    userId: number
}
const ProfileImg:FunctionComponent<Props> = ({userId}) => {
    const [user, setUser] = useState<UserDto|null>();
    const imageUrl = "https://icon-library.com/images/none-icon/none-icon-0.jpg";


    const fetchUser = async() => {
        const response = await UserService.getUserById(userId);
        setUser(response);
    }

    useEffect(()=> {
      fetchUser().then((_) => {})
    },[])
  return (
    <img className="notificationBannerImg"  src={user?.image ? `data:image/jpeg;base64,${user?.image?.picByte}` : imageUrl}/>
  )
}

export default ProfileImg
