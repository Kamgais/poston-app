import { createSlice } from "@reduxjs/toolkit";



const INITIAL_STATE:any = [];



export const notifSlice = createSlice({
    name: 'notifs',
    initialState: INITIAL_STATE,
    reducers: {
     
       
        getAndSetNotifs: (state, action) => {
            state = [...action.payload];
            return state;
        }
    }
})


export const {getAndSetNotifs} = notifSlice.actions;

export default notifSlice.reducer;