import React, {FunctionComponent, useEffect, useState} from 'react'
import { CategoryDto } from '../../types/CategoryDto';
import './category.styles/category.css';


type Props = {
    category: CategoryDto
}

const CategoryBox:FunctionComponent<Props> = ({category}) => {
const [color, setColor] = useState<string>('')

  useEffect(() => {

    switch(category.categoryName) {
        case 'cars' : setColor('#ff7675'); break;
        case 'life-style' : setColor('#74b9ff'); break;
        case 'music' : setColor('#fd79a8'); break;
        case 'science' : setColor('#81ecec'); break;
        case 'sport' : setColor('#2d3436'); break;
        case 'informatic' : setColor('#636e72'); break;
        default: setColor('#fab1a0');
    }
  
  }, [])
    const styles = {
        background: color
    }
  return (
    <div style={styles} className='categoryBox'>
       <i className={category.categoryIcon}></i> 
       <p>{category.categoryName?.toUpperCase()}</p>
      
    </div>
  )
}

export default CategoryBox
