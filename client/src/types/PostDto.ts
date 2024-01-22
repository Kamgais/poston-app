


export interface PostDto {
    id?:number,
    title?:string,
    dateCreated?: Date,
    content?:string,
    likeCount?:number,
    unlikeCount?:number,
    userId?:number,
    postImage?:string,
    categories?: string[]
   
}