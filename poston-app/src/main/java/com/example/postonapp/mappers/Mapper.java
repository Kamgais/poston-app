package com.example.postonapp.mappers;


public interface Mapper<T,Dto> {

    public T toEntity(Dto dto);
    public Dto toDto(T t);


}
