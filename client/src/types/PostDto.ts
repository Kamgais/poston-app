import { CategoryDto } from "./CategoryDto";
import { CommentDto } from "./CommentDto";
import { ImageDto } from "./ImageDto";
import { UserDto } from "./UserDto";


export interface PostDto {
    id?:number,
    title?:string,
    dateCreated?: Date,
    content?:string,
    likeCount?:number,
    unlikeCount?:number,
    user?:UserDto,
    image?:ImageDto,
    categories?: CategoryDto[]
   
}