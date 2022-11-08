import authReducer from './authSlice';
import postReducer from './postSlice';
import { configureStore } from "@reduxjs/toolkit";



export const store = configureStore({
    reducer: {
        auth: authReducer,
        posts: postReducer
    }
})