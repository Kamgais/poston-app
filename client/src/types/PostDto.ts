import { CategoryDto } from "./CategoryDto";
import { UserDto } from "./UserDto";


export interface PostDto {
    id?:number,
    title?:string,
    postImage?:string,
    dateCreated?: Date,
    likeCount?:number,
    unlikeCount?:number,
    user?:UserDto,
    categories?: CategoryDto[]
}