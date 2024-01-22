import React,{FunctionComponent} from 'react'
import Form from '../components/Form/Form'

const LoginPage:FunctionComponent = () => {


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
    <div style={containerStyles} className='loginContainer'>
      <Form type='login'/>
    </div>
  )
}

export default LoginPage
