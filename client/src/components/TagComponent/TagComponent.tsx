import React,{FunctionComponent, useState, useRef, Dispatch, SetStateAction, useEffect} from 'react'
import { TagDto } from '../../types/TagDto';
import './tagComponent.styles/tagComponent.css';


type Props = {
  username: string,
  setUsername: Dispatch<SetStateAction<string>>,
  tags: TagDto[]|null
}
const TagComponent:FunctionComponent<Props> = ({username,setUsername,tags}) => {
  
  
  useEffect(() => {
  // console.log(tags)
  },[tags])


  return (

    <div className='tagContainer'>
        <div className="tagElements">
            <ul className="tagList">
               {
                tags?.map((tag:any, index) => (
                  
                  <li className='tagListElement' key={index}>{tag.user.username}<i className="fa-regular fa-circle-xmark tagListIcon"></i></li>
                  
                  
                ))
               }
               
                
            </ul>
            <input  type="text" className='tagInput' placeholder='Tag a person'  onChange={(e) => setUsername(e.target.value)} />
        </div>
      
    </div>
  )
}

export default TagComponent
