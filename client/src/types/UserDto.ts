import { ImageDto } from "./ImageDto"

export interface UserDto {
    id?:number,
    username?:string,
    emailAddress?:string,
    password?:string
    image?: ImageDto
}