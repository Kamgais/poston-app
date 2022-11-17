import { createSlice } from "@reduxjs/toolkit";
import { PostDto } from "../types/PostDto";




let initialState: PostDto[];

if( localStorage.getItem('posts')) {
    initialState = JSON.parse(localStorage.getItem('posts')!);
} else {
    initialState = [];
}

   

export const postSlice = createSlice({
    name: 'posts',
    initialState: initialState,

    reducers: {
        addPost: (state,action) => {
            state.push(action.payload);
           // sessionStorage.setItem('posts', JSON.stringify(state))
        },
     
        removePost: (state,action) => {
            state = state.filter((post) => post.id !== action.payload);
           // sessionStorage.setItem('posts', JSON.stringify(state))
        },

        updatePost: (state, action) => {
            const index = state.findIndex(post => post.id === action.payload.id);
            state[index] = action.payload;
           // sessionStorage.setItem('posts', JSON.stringify(state))
        },

        getPosts: (state,action) => {
            state = action.payload.map((post:any) => post);
           // localStorage.setItem('posts', JSON.stringify(state))
             return state;
           
        },

        addLike: (state,action) => {
            const index = state.findIndex((post) =>  post.id === action.payload.id);
            state[index].likeCount = state[index].likeCount + action.payload.likeCounter;
           // sessionStorage.setItem('posts', JSON.stringify(state))
            return state;

        },

        addDislike: (state, action) => {
            const index = state.findIndex((post) =>  post.id === action.payload.id);
            state[index].unlikeCount = state[index].unlikeCount + action.payload.unLikeCounter;
           // sessionStorage.setItem('posts', JSON.stringify(state)) ;
            return state;
        }

    

    }
})



export const {addPost, removePost,updatePost,getPosts, addLike, addDislike} = postSlice.actions;


export default postSlice.reducer;