import { createSlice } from "@reduxjs/toolkit";
import { PostDto } from "../types/PostDto";



let INITIAL_STATE: PostDto[];

if(sessionStorage.getItem('posts')) {
    INITIAL_STATE = JSON.parse(sessionStorage.getItem('posts')!)
} else {
    INITIAL_STATE = []
}

export const postSlice = createSlice({
    name: 'posts',
    initialState: INITIAL_STATE,

    reducers: {
        addPost: (state,action) => {
            state.push(action.payload);
            sessionStorage.setItem('posts', JSON.stringify(state))
        },
     
        removePost: (state,action) => {
            state = state.filter((post) => post.id !== action.payload);
            sessionStorage.setItem('posts', JSON.stringify(state))
        },

        updatePost: (state, action) => {
            const index = state.findIndex(post => post.id === action.payload.id);
            state[index] = action.payload;
            sessionStorage.setItem('posts', JSON.stringify(state))
        },

        getPosts: (state,action) => {
            state = action.payload;
            sessionStorage.setItem('posts', JSON.stringify(state))
        },

        addLike: (state,action) => {
            const index = state.findIndex((post) =>  post.id === action.payload.id);
            state[index].likeCount = state[index].likeCount + action.payload.likeCounter;
            sessionStorage.setItem('posts', JSON.stringify(state))

        },

        addDislike: (state, action) => {
            const index = state.findIndex((post) =>  post.id === action.payload.id);
            state[index].unlikeCount = state[index].unlikeCount + action.payload.unLikeCounter;
            sessionStorage.setItem('posts', JSON.stringify(state)) ;
        }

    }
})



export const {addPost, removePost,updatePost,getPosts, addLike, addDislike} = postSlice.actions;


export default postSlice.reducer;