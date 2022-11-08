import React, {FunctionComponent} from 'react'
import {useSelector} from 'react-redux'
import {Navigate} from 'react-router-dom'

const PrivateRoute:FunctionComponent = () => {
    const {user, logged} = useSelector((state:any) => state.auth);

    if(!user || !logged) {
        <Navigate  to='/'/>
    }
      return (
     <Navigate to='posts'/>
  )
}

export default PrivateRoute
