import authReducer from './authSlice';
import postReducer from './postSlice';
import notifReducer from './notifSlice';
import { combineReducers, configureStore , } from "@reduxjs/toolkit";
import { persistStore, persistReducer, FLUSH, REHYDRATE, PAUSE, PERSIST, PURGE, REGISTER } from 'redux-persist';
import storage from 'redux-persist/lib/storage';






const persistConfig = {
    key: 'main-root',
    storage
}

const rootReducer = combineReducers({
    auth: authReducer,
    posts: postReducer,
    notifs: notifReducer
})



const persistedReducer  = persistReducer(persistConfig, rootReducer);


export const store = configureStore({
   reducer: persistedReducer,
   middleware : (getDefaultMiddleware) => {
       return getDefaultMiddleware({
           serializableCheck: {
            ignoredActions: [FLUSH, REHYDRATE , PAUSE, PERSIST, PURGE, REGISTER]
           }
       });
   },
   
   
})

 export const persistor = persistStore(store);