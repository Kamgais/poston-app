import { createSlice } from "@reduxjs/toolkit";


let INITIAL_STATE;


    INITIAL_STATE = {
        user: null,
        logged: false
    }





export const authSlice = createSlice({
    name: 'auth',
    initialState: INITIAL_STATE,

    reducers: {


        signIn: (state,action) => {
           state.user = action.payload;
           state.logged = true;

           return state;
           
        },

        signOut: (state) => {
            state.user = null;
            state.logged = false;

            return state;
           
        }
    }


});



//Actions creators 
export const {signIn, signOut} = authSlice.actions;


export default authSlice.reducer;