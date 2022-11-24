import React,{FunctionComponent, useState, useEffect, useContext} from 'react'
import { useNavigate } from 'react-router-dom';
import { notificationContext } from '../../context/NotificationContext';
import { useSelector } from 'react-redux';
import { CategoryService } from '../../services/CategoryService';
import { CategoryDto } from '../../types/CategoryDto';
import { ImageDto } from '../../types/ImageDto';
import { PostDto } from '../../types/PostDto';
import './createPost.styles/createPost.css';
import { ImageService } from '../../services/ImageService';
import { PostService } from '../../services/PostService';
import TagComponent from '../../components/TagComponent/TagComponent';
import UserList from '../../components/UserList/UserList';
import { TagDto } from '../../types/TagDto';
import { TagService } from '../../services/TagService';
import PostForm from '../../components/PostForm/PostForm';




const CreatePost:FunctionComponent = () => {
   
  return (
    <>
    <PostForm type='CREATE'/>
    </>
  )
}

export default CreatePost;
