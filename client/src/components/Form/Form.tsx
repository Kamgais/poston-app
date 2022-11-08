import React, {FunctionComponent, useState, useContext} from 'react'
import { notificationContext } from '../../context/NotificationContext';
import { useDispatch } from 'react-redux';
import { signIn } from '../../redux/authSlice';
import {useNavigate} from 'react-router-dom';
import './form.styles/form.css';
import Image from '../../assets/landing.png';
import { UserDto } from '../../types/UserDto';
import { AuthService } from '../../services/AuthService';


type Props = {
    type:string
}

type ValidField = {
  valid: boolean,
  msg:string
}

const Form:FunctionComponent<Props> = ({type}) => {
  const [user,setUser] = useState<UserDto|null>();
  const [validMessage, setValidMessage] = useState<string>();
  const dispatch = useDispatch();
  const navigate = useNavigate();
  const {handleNotification} = useContext(notificationContext);


  const handleChangeValueInput = (e: React.ChangeEvent<HTMLInputElement>) => {
    const newUser = {...user , [e.target.name] : e.target.value}
    setUser(newUser);
   
    
  }


  const handleSubmit = (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();

    if(type === 'register') {
      const isFormValid:ValidField = validForm();

      if(!isFormValid.valid) {
      setValidMessage(isFormValid.msg)
      } else if (isFormValid.valid) {
        setValidMessage(isFormValid.msg)
        AuthService.saveUser(user!)
        .then((response) => {
           if(!response) { handleNotification('error', 'error while creating a account!')}
          if(response) {handleNotification('success', 'successfull account created !')
          navigate('/login') 
        }
         
        })
        .catch((error) => console.log(error))

      }
    }

    else if( type === 'login') {
      AuthService.loginUser(user!)
      .then((response) => {
        if(!response) { handleNotification('error', 'error while logging'); }
        if(response) {
          handleNotification('success', 'successfull logged');
          dispatch(signIn(response));
          console.log(response);
          navigate('/posts');
          
        }
      })
      .catch((error) => console.log(error))
    }

    
  }



  const validForm = ():ValidField => {


    // validate username
    if(user?.username!.length! < 6 || user?.username!.length! > 30) {
      return { valid : false , msg: 'username must have 6 to 30 letters'}
    }


    // validate email
    if(!/^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/.test(user?.emailAddress!))  {
      return { valid: false , msg: 'email address is not valid'}
    }


    // validate passwords
const repeatPwd:HTMLInputElement|null = document.querySelector('#repeatpwd');

if(user?.password !== repeatPwd?.value) {
  return { valid: false , msg: 'passwords do not match'}
}

return { valid: true , msg: ''};

}








  return (
    <>
    
    <div className='formContainer'>
   
  
  <div className="formContainerIcon">
  <img src={Image} alt="" />
  </div>

  <form action="" className="formContainerForm"  onSubmit={(e) => handleSubmit(e)}>
    <input type="text" name='username' placeholder='username'  onChange={(e) => handleChangeValueInput(e)}/>
    <input type="email" name='emailAddress' placeholder='email address' onChange={(e) => handleChangeValueInput(e)} />
    <input type="password" name='password' placeholder='password' onChange={(e) => handleChangeValueInput(e)} />
    {type === 'register' && <input type="password" placeholder='repeat password' id='repeatpwd' />}
     <button className='submitButton'>{type.toUpperCase()}</button>
     <p style={{color : 'red', textAlign: 'center', fontFamily: 'Roboto'}}>{validMessage}</p>
  </form>

      
    </div>
    </>
  )
}

export default Form
