import React,{FunctionComponent, useState, useEffect, useContext} from 'react'
import { notificationContext } from '../../context/NotificationContext';
import { useSelector } from 'react-redux';
import { CategoryService } from '../../services/CategoryService';
import { CategoryDto } from '../../types/CategoryDto';
import { ImageDto } from '../../types/ImageDto';
import { PostDto } from '../../types/PostDto';
import './createPost.styles/createPost.css';
import { ImageService } from '../../services/ImageService';
import { PostService } from '../../services/PostService';


type ValidField = {
    valid: boolean,
    msg:string
}

const CreatePost:FunctionComponent = () => {
    const {user} = useSelector((state:any) => state.auth)
    const {handleNotification} = useContext(notificationContext);
    const [categories, setCategories] = useState<CategoryDto[]|null>();
    const [selectedCategories, setSelectedCategories] = useState<CategoryDto[]>([]);
    const [file,setFile] = useState<File|null>(null);
    const [post, setPost] = useState<PostDto|null>({
                                title: '',
                                dateCreated: new Date(),
                                likeCount: 0,
                                unlikeCount: 0,
                                user: user,
                                categories:[]


    });
    const [image,setImage] = useState<ImageDto|null>();
    const imageUrl = "https://icon-library.com/images/none-icon/none-icon-0.jpg";


    const fetchCategories = async() => {
        const response = await CategoryService.getAllCategories();
        setCategories(response);
    }

    useEffect(()=> {
     fetchCategories();
     
    },[])



    const onCategoriesArray = (e:React.ChangeEvent<HTMLInputElement>) => {
        let exist : boolean = false;
        let categoryIndex: number;
        selectedCategories?.forEach((category, index) => {
            if(e.target.name === category.categoryName) {
                exist = true;
                categoryIndex = index;
            }
        })

        if(exist){
          const newArrayCategories:CategoryDto[] = selectedCategories.filter((category) => category.categoryName !== e.target.name);
          setSelectedCategories(newArrayCategories);
          console.log(selectedCategories)
        }

        if(!exist) {
            const newArrayCategories:CategoryDto[] = [...selectedCategories, {categoryName: e.target.name}];
            setSelectedCategories(newArrayCategories);
            console.log(selectedCategories)
        }
       
    }




    const handleSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
        e.preventDefault();
        const validated: ValidField = formValid();

        if(!validated.valid) {
          handleNotification('error',validated.msg);
        } else {
            await uploadImage()
            const newPost:PostDto = {...post, categories: selectedCategories, image: image!}

            const response = await PostService.savePost(newPost);
        }






    }


    const formValid = ():ValidField => {

       if(selectedCategories.length === 0) {
        return {valid: false, msg: 'you must select at least one category '}
       } 

       if(post?.title?.trim().length === 0) {
        return {valid: false, msg: 'you must give a title' }
       } 

       if(post?.content?.trim().length === 0) {
        return {valid: false , msg: 'you must write a content'}
       }
       return {valid: true , msg: 'post created'}

    }



    const handleChange = (e: React.ChangeEvent<HTMLInputElement| HTMLTextAreaElement>) => {
       const newPost = {...post, [e.target.name]: e.target.value};
       setPost(newPost); 
       //console.log(post);
    }


    const uploadImage = async() => {
        
            const data = new FormData();
            data.append('file',file!, Date.now()+file!.name);
            console.log(data)
            const response = await ImageService.uploadImage(data);
            console.log(response)
            setImage(response);
            const newImage = await ImageService.getImage(response?.name!)
            setImage(newImage);
            
       
        
    }





















  return (
    <div className='createPostContainer'>
      <div className="categoryList">
        <h3>Category</h3>
        <form>
        {
            categories?.map((category:any, index) => (
                <div key={index}>
                <input onChange={(e) => onCategoriesArray(e)} name={category.categoryName} type="checkbox"/>
                <label className='categoryLabel'>{category.categoryName}</label>
                
                </div>
            ))
        }
        </form>
      </div>
      <form className="createPostForm"  onSubmit={(e) => handleSubmit(e)}>
        <div className="postImage">
            <img src={ !file ?  imageUrl : URL.createObjectURL(file!)} alt="" />
            <label htmlFor="postImage"><i className="fa-solid fa-circle-plus"></i></label>
            <input type="file"  id='postImage' style={{display: 'none'}} onChange={(e) =>setFile(e.target.files![0])}/>

        </div>

       <div className="titleInput">
       <input type="text" name='title'  placeholder='Title here...'  onChange={(e) => handleChange(e)}/>
       </div>

       <div className="PostContent">
        <textarea name="content" id=""  onChange={(e) => handleChange(e)} ></textarea>
       </div>

       <div className="createPostActions">
       <i className="fa-solid fa-circle-plus"></i>
       <button type='submit'>Post</button>
       </div>



      </form>
    </div>
  )
}

export default CreatePost;
