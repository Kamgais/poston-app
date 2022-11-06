import React, {FunctionComponent} from 'react'
import './form.styles/form.css';


type Props = {
    type:string
}

const Form:FunctionComponent<Props> = ({type}) => {
  return (
    <div className='formContainer'>
  
  <div className="formContainerIcon">
  <i className="fa-regular fa-circle-user"></i>
  </div>

  <form action="" className="formContainerForm">
    <input type="text"  placeholder='username'/>
    <input type="email" placeholder='email address' />
    <input type="password" placeholder='password' />
    {type === 'register' && <input type="text" placeholder='repeat password' />}
    { type === 'login' ? <button className='loginButton'>LOGIN</button> : <button className='registerButton'>REGISTER</button>}
  </form>

      
    </div>
  )
}

export default Form
