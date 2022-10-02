package com.example.demo.dao;

import java.util.List;
import com.example.demo.model.Image;
import com.example.demo.model.ImageDetail;

public interface ImageDao {
     
    List<Image> getAll();

    Image getById(Long imageId);

    ImageDetail getDetailById(Long imageId);

    List<ImageDetail> getDetailByRange(Long albumId, int chunkSize, int chunSection, String filterName);
  
    Image getByName(String imageName); 

    ImageDetail create(ImageDetail imageDetail);
 
    void modify(ImageDetail imageDetail); 

    void delete(Image image);   

}