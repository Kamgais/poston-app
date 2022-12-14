import React from 'react';
import ReactDOM from 'react-dom/client';
import './styles/index.css';
import App from './App';
import { Provider } from 'react-redux';
import {store, persistor} from './redux/store';
import { BrowserRouter } from 'react-router-dom';
import { PersistGate } from 'redux-persist/integration/react';
import NotificationContext from './context/NotificationContext';



const root = ReactDOM.createRoot(
  document.getElementById('root') as HTMLElement
);
root.render(
  <React.StrictMode>
    <BrowserRouter>
    <Provider store={store}>
     <PersistGate loading={null} persistor={persistor}>
    <NotificationContext>
    <App />
    </NotificationContext>
    </PersistGate> 
    </Provider>
    </BrowserRouter>
  </React.StrictMode>
);


