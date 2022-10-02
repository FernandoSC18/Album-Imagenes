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

import com.example.demo.dao.ImageDao;
import com.example.demo.model.Image;
import com.example.demo.model.ImageDetail;
import com.example.demo.model.Response; 

@RestController
@RequestMapping("/api")
public class ImageController {
        
    @Autowired
    ImageDao imageDao;

    @GetMapping("/images") 
    public Response<List<Image>> getAllImages () { 
 
        return new Response<List<Image>> (200, null, "OK", imageDao.getAll()) ;
    }

    @GetMapping("/images/id/{imageIid}") 
    public Response<Image> getImageById (@PathVariable(required=true,name="imageIid") Long imageIid) {
         
        return new Response<Image> (200, null, "OK", imageDao.getById(imageIid)) ;
    }

    /*
     * ImageDetail return an image whit more detail than Image, this return an album and file whit mach by ID
     */
    @GetMapping("/images/id_detail/{imageIid}") 
    public Response<ImageDetail> getImageDetailById (@PathVariable(required=true,name="imageIid") Long imageIid) {      
 
        return new Response<ImageDetail> (200, null, "OK", imageDao.getDetailById(imageIid)) ;
    }

    /*
     * return an List of Images for chunk elemnts and load when user needs
     */
    @GetMapping( value={"/images/chunk_detail/{chunkSize}/{chunSection}/album/{albumId}",
        "/images/chunk_detail/{chunkSize}/{chunSection}/album/{albumId}/{filterName}"}) 
    public Response<List<ImageDetail>> getImageDetailByRange (
        @PathVariable(required=true,name="chunkSize") int chunkSize,
        @PathVariable(required=true,name="chunSection") int chunSection,
        @PathVariable(required=true,name="albumId") Long albumId,
        @PathVariable(required=false,name="filterName") String filterName) {  
 
        return new Response<List<ImageDetail>> (200, null, "OK",
            imageDao.getDetailByRange(albumId, chunkSize, chunSection, filterName)) ;
    }

    @GetMapping("/images/name/{imageName}") 
    public Response<Image> getImageByName (@PathVariable(required=true,name="imageName") String imageName) { 
 
        return new Response<Image> (200, null, "OK", imageDao.getByName(imageName)) ;
    }

    @PostMapping("/images") 
    public Response<Object> addImage ( @RequestBody ImageDetail imageDetail ) {
        imageDetail.setCreatedDate(new Date(new java.util.Date().getTime())); 
 
        //Return new image created by get ID and show on frontend for edit options
        return new Response<Object> (200, null, "OK, new image add succesfully", imageDao.create(imageDetail)) ;
    }

    @PutMapping("/images") 
    public Response<Object> editImage (@RequestBody ImageDetail imageDetail) {
        imageDetail.setUpdatedDate(new Date(new java.util.Date().getTime()));
        imageDao.modify(imageDetail); 
 
        return new Response<Object> (200, null, "OK, edit image succesfully", null) ;
    }

    @DeleteMapping("/images") 
    public Response<Object> deleteImage (@RequestBody Image image) {
        imageDao.delete(image); 

        return new Response<Object> (200, null, "OK, delete image succesfully", null) ;
    }


}
