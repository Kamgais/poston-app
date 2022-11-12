import { PostDto } from "./PostDto";
import { UserDto } from "./UserDto";



export interface CommentDto {

    id?: string,
    message?: string,
    user?: UserDto,
    post?: PostDto

}