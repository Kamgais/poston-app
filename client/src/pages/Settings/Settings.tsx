import React, {FunctionComponent, useState, useContext}from 'react'
import { notificationContext } from '../../context/NotificationContext';
import { useSelector, useDispatch } from 'react-redux';
import { useNavigate } from 'react-router-dom';
import { signIn, signOut } from '../../redux/authSlice';
import { UserDto } from '../../types/UserDto';
import './settings.styles/settings.css';
import { ImageService } from '../../services/ImageService';
import { UserService } from '../../services/UserService';
type ValidField = {
    valid: boolean,
    msg:string
  }
const Settings: FunctionComponent = () => {
    const {user} = useSelector((state:any) => state.auth);
    const {handleNotification} = useContext(notificationContext);
    const dispatch = useDispatch();
    const navigate = useNavigate();
    const [file, setFile] = useState<File|null>(null);
    const [updatedUser, setUpdatedUser] = useState<UserDto|null>({
                                                id: user.id,
                                               username : user.username,
                                               emailAddress: user.emailAddress,
                                               password: '',
                                               image: user.image

    })
    const imageUrl = "https://icon-library.com/images/none-icon/none-icon-0.jpg";



    const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        const newUser = {...updatedUser, [e.target.name]: e.target.value}
        setUpdatedUser(newUser);
        console.log(newUser);
    }


    const handleSubmit = (e: React.FormEvent<HTMLFormElement>) => {
        e.preventDefault();
        const isFormValid: ValidField = validForm();

        if(!isFormValid.valid) {
          handleNotification("error", isFormValid.msg);
        } else {
          saveImageAndUpdateUser()
          .then((_) => {handleNotification("success", "updated successfull");})  
          .catch((err) => console.log(err))

        }

    }


    const deleteProfile = async() => {
        const msg = await UserService.deleteUserAccount(user.id);
        handleNotification("success", msg!);
        dispatch(signOut());
        

    }


    const saveImageAndUpdateUser = async() => {
        let newImage:any;
        if(file) {
            const data = new FormData();
            data.append('file',file!, Date.now()+file!.name);
            console.log(data)

            const response = await ImageService.uploadImage(data);
            console.log(response)
            newImage = await ImageService.getImage(response?.name!)
        }

        if(!file && user.image) {
            newImage = user.image;
        }

        if(!file && !user.image) {
            newImage = null;
        }

        const newUser = {...updatedUser, image: newImage};
        const response = await UserService.updateUserAccount(user.id, newUser);
        dispatch(signIn(response));

    }


    const validForm = ():ValidField => {


        // validate username
        if(updatedUser?.username!.length! < 6 || updatedUser?.username!.length! > 30) {
          return { valid : false , msg: 'username must have 6 to 30 letters'}
        }
    
    
        // validate email
        if(!/^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/.test(updatedUser?.emailAddress!))  {
          return { valid: false , msg: 'email address is not valid'}
        }
    
    return { valid: true , msg: ''};
    
    }


  return (
    <div className='settings--container'>
       <div className="settings--container--profile">
        <img src={file? URL.createObjectURL(file!) : user.image ? `data:image/jpeg;base64,${user?.image?.picByte}` : imageUrl} alt="" />
        <label htmlFor="profile-img"><i className="fa-solid fa-circle-plus"></i></label>
        <input  type="file"  id='profile-img'   style={{display: 'none'}}  onChange={(e) => setFile(e.target.files![0])}/>
        </div> 
        <div className="settings--container--form">
            <form action="#" onSubmit={handleSubmit}>
                <label htmlFor="username">Username</label>
                <input type="text" id='username' name='username' placeholder={user.username}  onChange={(e) => handleChange(e)}/>
                <label htmlFor="email-address">Email-address</label>
                <input type="email" id='email-address' name='emailAddress' placeholder={user.emailAddress} onChange={(e) => handleChange(e)} />
                <label htmlFor="password"> Set a new password</label>
                <input type="password" id='password' name='password' onChange={(e) => handleChange(e)}/>
                
                <div className="settings--container--form--actions">
                <button type='submit' >Update Profile</button>
                <button type='button' onClick={deleteProfile}>Delete Account</button>
                </div>
                
            </form>
        </div>
      
    </div>
  )
}

export default Settings
