import { PostDto } from "./PostDto";
import { UserDto } from "./UserDto";

export interface TagDto {
    id?: number,
    post?: PostDto,
    user?: UserDto
}