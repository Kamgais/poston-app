import React, {FunctionComponent, useContext, useEffect, useState} from 'react'
import { useSelector } from 'react-redux';
import { useNavigate } from 'react-router-dom';
import { notificationContext } from '../../context/NotificationContext';
import { CategoryService } from '../../services/CategoryService';
import { ImageService } from '../../services/ImageService';
import { PostService } from '../../services/PostService';
import { TagService } from '../../services/TagService';
import { CategoryDto } from '../../types/CategoryDto';
import { ImageDto } from '../../types/ImageDto';
import { PostDto } from '../../types/PostDto';
import { TagDto } from '../../types/TagDto';
import TagComponent from '../TagComponent/TagComponent';
import UserList from '../UserList/UserList';


type ValidField = {
    valid: boolean,
    msg:string
}


type Props = {
    type: string,
    body?: PostDto|null
}

const PostForm: FunctionComponent<Props> = ({type, body}) => {
    const {user} = useSelector((state:any) => state.auth)
    const {handleNotification} = useContext(notificationContext);
    const navigate = useNavigate();
    const [categories, setCategories] = useState<CategoryDto[]|null>();
    const [selectedCategories, setSelectedCategories] = useState<CategoryDto[]>([]);
    const [file,setFile] = useState<File|null>(null);
    const [post, setPost] = useState<PostDto|null>({
                                title: '',
                                dateCreated: new Date(),
                                likeCount: 0,
                                unlikeCount:  0,
                                user: user,
                                categories: [],
                                content: ''


    });
    const [image,setImage] = useState<ImageDto|null>();
    const [usernameSearched, setUsernameSearch] = useState<string>('');
    const [tags, setTags] = useState<TagDto[]|null>([]);
    const imageUrl = "https://icon-library.com/images/none-icon/none-icon-0.jpg";


    const fetchCategories = async() => {
        const response = await CategoryService.getAllCategories();
        setCategories(response);
      //  console.log(response)
    }

    const fetchTags = async() => {
        const response = await TagService.getTagsByPostId(body?.id!);
       // console.log(response)
        setTags(response)
        
    }

    useEffect(()=> {
     fetchCategories().then((c) => {
     
     });

    

   body && fetchTags()


   // initialise post by updating
  body && setPost({
    title: body?  body?.title : '',
    dateCreated: body? body?.dateCreated : new Date(),
    likeCount: body? body.likeCount : 0,
    unlikeCount: body? body?.unlikeCount : 0,
    user: user,
    categories: body ? body.categories : [],
    content: body? body.content : ''
})
    
     },[body])



    useEffect(()=> {
        if(body?.categories) {
            const newSelectedCategories = body?.categories;
           // console.log(newSelectedCategories)
            setSelectedCategories(newSelectedCategories!)

} 
        
       })



    const onCategoriesArray = (e:React.ChangeEvent<HTMLInputElement>) => {
        let exist : boolean = false;
        let categoryIndex: number;
        selectedCategories?.forEach((category: CategoryDto, index: number) => {
            if(e.target.name === category.categoryName) {
                exist = true;
                categoryIndex = index;
            }
        })

        if(exist){
          const newArrayCategories:CategoryDto[] = selectedCategories.filter((category: CategoryDto) => category.categoryName !== e.target.name);
          setSelectedCategories(newArrayCategories);
         // console.log(selectedCategories)
        }

        if(!exist) {
            const newArrayCategories:CategoryDto[] = [...selectedCategories, {categoryName: e.target.name}];
            setSelectedCategories(newArrayCategories);
         //   console.log(selectedCategories)
        }
       
    }




    const handleSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
        e.preventDefault();
        const validated: ValidField = formValid();

        if(!validated.valid) {
          handleNotification('error',validated.msg);
        } else {
            await uploadImageAndCreatePostAndSaveTags()
            handleNotification('success', validated.msg);
            navigate('/posts')
           
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

       if(file === null && !body) {
        return {valid: false,  msg: 'you must set a image'}
       }

       if( file && file!.size > 300 && !body) {
        return {valid: false, msg: 'image size must smaller than 300ko'}
       }
       return {valid: true , msg: 'post created'}

    }



    const handleChange = (e: React.ChangeEvent<HTMLInputElement| HTMLTextAreaElement>) => {
       const newPost = {...post, [e.target.name]: e.target.value};
       setPost(newPost); 
       console.log(post);
    }


    const uploadImageAndCreatePostAndSaveTags = async() => {
        let newImage:any;
        let newPost:any;
        if(file) {
             const data = new FormData();
            data.append('file',file!, Date.now()+file!.name);
            console.log(data)

            const response = await ImageService.uploadImage(data);
            console.log(response)

            setImage(response);
             newImage = await ImageService.getImage(response?.name!)

            setImage(newImage);

        }

         if(body && file) {
          newPost = {...post, categories: selectedCategories, image: newImage!}
            
        }

        if(body && !file) {
           newPost = {...post , categories: selectedCategories, image: body.image, id: body.id} 
        }


        if(body) {
          const postResponse = await PostService.updatePost(newPost, body.id!) ; 

          const newTags = [...tags!];
            newTags.forEach((tag) => {
               tag.post = postResponse!;
            })

            const tagsResponse = await TagService.updateTags(newTags, postResponse?.id!);

        }

        if(!body) {
            const postResponse = await PostService.savePost(newPost);

            const newTags = [...tags!];
            newTags.forEach((tag) => {
               tag.post = postResponse!;
            })

            const tagsResponse = await TagService.createTags(newTags);

        }
            
           
            

            

            

            
            
    }

return (
    <div className='createPostContainer'>
      <div className="categoryList">
        <h3>Category</h3>
        <form>
        {
            categories?.map((category:any, index: React.Key | null | undefined) => (
                <div key={index}>
                <input onChange={(e) => onCategoriesArray(e)} name={category.categoryName} type="checkbox"  checked= {selectedCategories.filter(e => e.id === category.id).length > 0 &&  true}/>
                <label className='categoryLabel'>{category.categoryName}</label>
                
                </div>
            ))
        }
        </form>
      </div>
      <form className="createPostForm"  onSubmit={(e) => handleSubmit(e)}>
        <div className="postImage">
            <img src={file?  URL.createObjectURL(file!) : body ? `data:image/jpeg;base64,${body?.image?.picByte}` : imageUrl} alt="" />
            <label htmlFor="postImage"><i className="fa-solid fa-circle-plus"></i></label>
            <input type="file"  id='postImage' style={{display: 'none'}} onChange={(e) =>setFile(e.target.files![0])}/>

        </div>

       <div className="titleInput">
       <input type="text" name='title'  placeholder={body? body.title : 'Title here...'}  onChange={(e) => handleChange(e)}/>
       </div>

       <div className="PostContent">
        <textarea name="content" id=""  onChange={(e) => handleChange(e)} placeholder= {body? body.content : 'write your text here'} ></textarea>
       </div>

       <div className="createPostActions">
      
       { usernameSearched.length > 0  && <UserList username={usernameSearched}  setTags={setTags} setUsername={setUsernameSearch}  tags={tags}/>} 
       <TagComponent username={usernameSearched} setUsername={setUsernameSearch}  tags={tags}/>
       <button type='submit' >{type}</button>
       </div>



      </form>
    </div>
  )
}

export default PostForm
