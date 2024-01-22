import React,{FunctionComponent, useEffect, useState} from 'react'
import { CategoryService } from '../../services/CategoryService';
import { CategoryDto } from '../../types/CategoryDto'
import CategoryBox from '../CategoryBox/CategoryBox';
import './categories.styles/categories.css';

const CategoryList:FunctionComponent = () => {
    const [categories, setCategories] = useState<CategoryDto[]|null>();

    const fetchCategories = async() => {
       const response = await  CategoryService.getAllCategories();
       setCategories(response);
       console.log(response);
    }

    useEffect(() => {
     fetchCategories();
    },[])
  return (
    <div className='categoryList'>
      
        {
            categories?.map((category, index) => (
                <CategoryBox key={index}   category = {category}/>
            ))
        }
      
    </div>
  )
}

export default CategoryList
