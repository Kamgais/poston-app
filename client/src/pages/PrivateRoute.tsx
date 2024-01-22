import React, {FunctionComponent} from 'react'
import {useSelector} from 'react-redux'
import {Navigate} from 'react-router-dom'


type Props = {
  children : JSX.Element
}
const PrivateRoute:FunctionComponent<Props> = ({children}) => {
    const {user, logged} = useSelector((state:any) => state.auth);

    if(!user || !logged) {
      return  <Navigate  to='/login'/>
    }
      return (
     children
  )
}

export default PrivateRoute
