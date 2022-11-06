import React, {FunctionComponent} from 'react'
import Form from '../components/Form/Form'

const RegisterPage:FunctionComponent = () => {
    const containerStyles = {
        width: '100vw',
        height:'100vh',
        display:'flex',
        justifyContent: 'center',
        alignItems: 'center',
        backgroundImage: 'url("https://static.vecteezy.com/system/resources/previews/000/201/483/original/vector-inside-office-illustration.jpg")',
        backgroundPosition: 'center',
        backgroundSize: 'cover'
    }
  return (
    <div    style={containerStyles} className='registerContainer'>
      <Form type='register'/>
    </div>
  )
}

export default RegisterPage
