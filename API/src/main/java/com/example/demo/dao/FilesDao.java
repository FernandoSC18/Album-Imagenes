package com.example.demo.dao;
 
import com.example.demo.model.Files;

public interface FilesDao { 

    Files getById(Long fileId); 

    void create(Files files);
 
    void modify(Files files);

    void delete(Files files);    

}