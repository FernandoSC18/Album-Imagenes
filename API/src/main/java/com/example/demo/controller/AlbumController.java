package com.example.demo.controller;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping; 
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.AlbumDao;
import com.example.demo.model.Album;
import com.example.demo.model.Response; 

@RestController
@RequestMapping("/api")
public class AlbumController {
    
    @Autowired
    AlbumDao albumDao;
  
    @GetMapping("/albums") 
    public Response<List<Album>> getAllAlbums(){   

        return new Response<List<Album>> (200, null, "OK", albumDao.getAll() );
    }
 
    @GetMapping("/albums/id/{albumId}") 
    public Response<Album> getAlbumById(@PathVariable(required=true,name="albumId") Long albumId){  
        
        return new Response<Album> (200, null, "OK", albumDao.getById(albumId) );
    }

    @GetMapping("/albums/name/{albumName}") 
    public Response<Album> getAlbumByName(@PathVariable(required=true,name="albumName") String albumName){   

        return new Response<Album> (200, null, "OK", albumDao.getByName(albumName) );
    }
        
    @PostMapping("/albums") 
    public Response<Object> addAlbum (@RequestBody Album album){  
        album.setAlbumId(null);
        album.setCreatedDate(new Date(new java.util.Date().getTime()));
        Album newAlbum = albumDao.create(album);

        return new Response<Object> (200, null, "OK, add album succesfully", newAlbum) ;
    }
     
    @PutMapping("/albums") 
    public Response<Object> editAlbum(@RequestBody Album album){     
        album.setUpdatedDate(new Date(new java.util.Date().getTime()));
        albumDao.modify(album);

        return new Response<Object> (200, null, "OK, modify album succesfully", null) ;
    }
     
    @DeleteMapping("/albums") 
    public Response<Object> deleteAlbum(@RequestBody Album album){  
        albumDao.delete(album);

        return new Response<Object> (200, null, "OK, delete album succesfully", null) ;
    }

}
