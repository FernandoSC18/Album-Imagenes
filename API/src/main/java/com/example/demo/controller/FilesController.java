package com.example.demo.controller; 

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping; 
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.FilesDao;
import com.example.demo.model.Files;
import com.example.demo.model.Response; 

@RestController
@RequestMapping("/api")
public class FilesController {
    
    @Autowired
    FilesDao filesDao;
   
    @GetMapping("/files/{fileId}") 
    public Response<Files> getFileById(@PathVariable(required=true,name="fileId") Long fileId){   

        return new Response<Files> (200, null, "OK", filesDao.getById(fileId) );
    } 
        
    @PostMapping("/files") 
    public Response<Object> addFile (@RequestBody Files files){  
        files.setCreatedDate(new Date(new java.util.Date().getTime()));
        filesDao.create(files);

        return new Response<Object> (200, null, "OK, Add file succesfully", null) ;
    }
     
    @PutMapping("/files") 
    public Response<Object> editFile (@RequestBody Files files){     
        files.setUpdatedDate(new Date(new java.util.Date().getTime()));
        filesDao.modify(files);

        return new Response<Object> (200, null, "OK, Modify file succesfully", null) ;
    }
     
    @DeleteMapping("/files") 
    public Response<Object> deleteFile (@RequestBody Files files){  
        filesDao.delete(files);
        
        return new Response<Object> (200, null, "OK, delete file succesfully", null) ;
    }

} 