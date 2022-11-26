import React , {useContext} from 'react';
import { useSelector } from 'react-redux';
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
import PrivateRoute from './pages/PrivateRoute';
import Notifications from './pages/Notifications/Notifications';
import UpdatePost from './pages/UpdatePost/UpdatePost';
import Settings from './pages/Settings/Settings';
import MyPosts from './pages/MyPosts/MyPosts';

function App() {
   const {user, logged} = useSelector((state:any) => state.auth)
  const {notification, handleNotification} = useContext<INotificationContext>(notificationContext);
  return (
    <div className="App">
    <header >
     <Navbar/>
     <Notification  type={notification!.type} msg={notification!.msg}/>
    </header>
    <main>
      <Routes>
        <Route path='/signup' element = {<RegisterPage/>}/>
        <Route path='/login' element = {<LoginPage/>}/>
        <Route path='/posts' element={<PrivateRoute><Posts/></PrivateRoute>}/>
        <Route path='/posts/:id' element={<PrivateRoute><SinglePost/></PrivateRoute>}/>
        <Route path='/posts/add' element={<PrivateRoute><CreatePost/></PrivateRoute>}/>
        <Route path='/posts/update/:id' element={<PrivateRoute><UpdatePost/></PrivateRoute>}/>
        <Route path='/notifs' element={<PrivateRoute><Notifications/></PrivateRoute>}/>
        <Route path='/settings' element={<PrivateRoute><Settings/></PrivateRoute>}/>
        <Route path='/my--posts' element={<PrivateRoute><MyPosts/></PrivateRoute>}/>
        <Route path='*' element={<Navigate to='/login'/>}/>
      </Routes>
    </main>
    { user !== null && logged && <footer className='footer' style={{marginTop: 'auto'}}>
      <Footer/>
    </footer>}
    </div>
  );
}

export default App;
