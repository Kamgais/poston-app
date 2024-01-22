import React, {FunctionComponent} from 'react'
import './footer.styles/footer.css';

const Footer:FunctionComponent = () => {
  return (
    <div className='footerContainer'>
        <div className="footerBar"></div>
        <div className="footerInfos">
            <p>© poston-app</p>
        </div>
      
    </div>
  )
}

export default Footer
