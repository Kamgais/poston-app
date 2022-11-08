import React, {FunctionComponent, useState} from 'react'
import { createContext } from 'react'

export interface INotificationContext {
   
    notification: State|undefined
    handleNotification: (type:string, msg:string) => void
}

type State = {
    type: string,
    msg:string
}


type Props = {
    children : JSX.Element
}

export const notificationContext = createContext<INotificationContext>({notification: {type: 'none', msg: ''}, handleNotification(){return;}});


const NotificationContext:FunctionComponent<Props> = ({children}) => {
    const [notification, setNotification] = useState<State>({type: 'none', msg: ''})

    const handleNotification = (type: string, msg:string) => {
        setNotification({type:type, msg:msg});
    }
  return (
   <notificationContext.Provider value={{notification, handleNotification}}>
   {children}
   </notificationContext.Provider>
  )
}

export default NotificationContext
