package com.example.demo.dao;

import java.util.List;
import com.example.demo.model.Album;

public interface AlbumDao {
     
    List<Album> getAll();

    Album getById(Long albumId);
    
    Album getByName(String albumName); 

    Album create(Album album);
 
    void modify(Album album);

    void delete(Album album);   

}
