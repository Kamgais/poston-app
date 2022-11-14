import React , {useContext} from 'react';
import './App.css';
import Navbar from './components/Navbar/Navbar';
import LoginPage from './pages/LoginPage';
import RegisterPage from './pages/RegisterPage';
import {Routes, Route, Navigate} from 'react-router-dom';
import Notification from './components/Notification/Notification';
import { notificationContext } from './context/NotificationContext';
import { INotificationContext } from './context/NotificationContext';
import Posts from './pages/Posts/Posts';
import Footer from './components/Footer/Footer';
import SinglePost from './pages/SinglePost/SinglePost';
import CreatePost from './pages/CreatePost/CreatePost';

function App() {

  const {notification, handleNotification} = useContext<INotificationContext>(notificationContext);
  return (
    <div className="App">
    <header >
     <Navbar/>
     <Notification  type={notification!.type} msg={notification!.msg}/>
    </header>
    <main style={{marginBottom: '100px'}}>
      <Routes>
        <Route path='/signup' element = {<RegisterPage/>}/>
        <Route path='/login' element = {<LoginPage/>}/>
        <Route path='/posts' element={<Posts/>}/>
        <Route path='/posts/:id' element={<SinglePost/>}/>
        <Route path='/posts/add' element={<CreatePost/>}/>
        <Route path='*' element={<Navigate to='/login'/>}/>
      </Routes>
    </main>
    <footer className='footer'>
      <Footer/>
    </footer>
    </div>
  );
}

export default App;
