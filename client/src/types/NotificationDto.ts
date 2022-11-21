import { PostDto } from "./PostDto";

export interface NotificationDto {
    id?:number,
    message?:string,
    dateCreated?:Date,
    read?: boolean,
    userId?: number,
    post?: PostDto
}