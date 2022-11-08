import { wait } from '@testing-library/user-event/dist/utils';
import React, {FunctionComponent, useEffect, useRef} from 'react'
import './notification.styles/notification.css';


type Props = {
    type:string,
    msg:string
}

const Notification:FunctionComponent<Props> = ({type,msg}) => {
 
    const divRef = useRef<HTMLDivElement|null>(null);


    useEffect(() => {

        
   if(type !== 'none' && msg !== '') {
    divRef.current!.style.top = '50px';
    setTimeout(()=> {
        divRef.current!.style.top = '-150px';
       }, 2000)
   }
     
      


    })

    const color = () => {
        switch(type) {
            case 'info' : return 'green';
            case 'success' : return '#327AE7';
            case 'error' : return 'red';
            default : return '';
        }
    }

    const containerStyles = {
        backgroundColor:  color()
    }


  return (
    <div  ref={divRef}
       style={containerStyles}   className='notificationContainer'>
        <div className="iconContainer">
            { type === 'info' && <p>?</p>}
            { type === 'success' && <i className="fa-regular fa-circle-check"></i> }
            { type === 'error' && <p>X</p>}
        </div>
      <div className="messageContainer">
        {msg}
      </div>
    </div>
  )
}

export default Notification
