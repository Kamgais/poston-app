import { createSlice } from "@reduxjs/toolkit";



const INITIAL_STATE:any = [];



export const notifSlice = createSlice({
    name: 'notifs',
    initialState: INITIAL_STATE,
    reducers: {
     
       
        getAndSetNotifs: (state, action) => {
            state = [...action.payload];
            return state;
        },

        read: (state, action) => {
            state.forEach((notif:any, index:any) => {
                if(notif.id === action.payload) {
                    notif.read = true;
                }
           

            });
            return state;
        }
    }
})


export const {getAndSetNotifs, read} = notifSlice.actions;

export default notifSlice.reducer;