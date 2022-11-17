import React, {Dispatch, FunctionComponent, SetStateAction, useEffect, useState} from 'react'
import { UserService } from '../../services/UserService'
import { TagDto } from '../../types/TagDto';
import { UserDto } from '../../types/UserDto'
import './userList.styles/userList.css';


type Props = {
    username: string,
    setTags: Dispatch<SetStateAction<TagDto[]|null>>,
    setUsername: Dispatch<SetStateAction<string>>,
    tags:TagDto[]|null
}
const UserList:FunctionComponent<Props> = ({username, setTags,setUsername,tags}) => {
    const [users,setUsers] = useState<UserDto[]|null>();


    const fetchUsers = async() => {
        const response = await UserService.getUserByUsername(username);
        setUsers(response);
    }

    const selectUserTag = (user: UserDto) => {
        let isInTags = false;
        tags?.forEach((tag) => {
            if(tag.user?.id === user.id) {
                isInTags = true;
            }
        })
       !isInTags && setTags(prevState => [...prevState!, {user:user}]);
      // console.log(tags);
        setUsername('');
        
    }

    useEffect(() => {
     fetchUsers(); 
    // console.log(tags);
    },[username])
  return (
    <div className='userListContainer'>

        <div className="userListElement">

            {
                users?.map((user:any, index) => (
                    <div  className='userList' onClick={() =>selectUserTag(user)}  key={index}>
                        <img src={user.profilePic} alt="" />
                    <p className="userListElementUsername">
                    {user.username}
            </p>
            </div>
                ))
            }
            
        </div>
      
    </div>
  )
}

export default UserList
