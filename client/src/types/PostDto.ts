import { CategoryDto } from "./CategoryDto";
import { CommentDto } from "./CommentDto";
import { UserDto } from "./UserDto";


export interface PostDto {
    id?:number,
    title?:string,
    postImage?:string,
    dateCreated?: Date,
    content?:string,
    likeCount?:number,
    unlikeCount?:number,
    user?:UserDto,
    categories?: CategoryDto[]
   
}