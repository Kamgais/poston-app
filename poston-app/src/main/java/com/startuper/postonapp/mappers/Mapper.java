package com.startuper.postonapp.mappers;


public interface Mapper<T,Dto> {

    T toEntity(Dto dto);
     Dto toDto(T t);


}
