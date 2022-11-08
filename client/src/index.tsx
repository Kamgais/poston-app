import React from 'react';
import ReactDOM from 'react-dom/client';
import './styles/index.css';
import App from './App';
import { Provider } from 'react-redux';
import {store} from './redux/store';
import { BrowserRouter } from 'react-router-dom';
import NotificationContext from './context/NotificationContext';



const root = ReactDOM.createRoot(
  document.getElementById('root') as HTMLElement
);
root.render(
  <React.StrictMode>
    <BrowserRouter>
    <Provider store={store}>
    <NotificationContext>
    <App />
    </NotificationContext>
    </Provider>
    </BrowserRouter>
  </React.StrictMode>
);


