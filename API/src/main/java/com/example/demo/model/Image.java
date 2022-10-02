package com.example.demo.model;
 
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id; 
import javax.persistence.Table; 
 
@Entity
@Table(name = "image")
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    @Column(name="image_id")
    private Long imageId;
 
    @Column(name="image_name")
    private String imageName;
 
    @Column(name="image_description")
    private String imageDescription;

    @Column(name="created_date")
    private Date createdDate;
    
    @Column(name="updated_date")
    private Date updatedDate;

    @Column(name="album_id")
    private Long albumId;
 
    @Column(name="file_id")
    private Long fileId;

    public Image( ) {  
    } 

    public Image( String imageName, String imageDescription, Date createdDate, Date updatedDate,
            Long albumId, Long fileId) { 
        this.imageName = imageName;
        this.imageDescription = imageDescription;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.albumId = albumId;
        this.fileId = fileId;
    }

    public Long getImageId() {
        return imageId;
    }

    public void setImageId(Long imageId) {
        this.imageId = imageId;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getImageDescription() {
        return imageDescription;
    }

    public void setImageDescription(String imageDescription) {
        this.imageDescription = imageDescription;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public Long getAlbumId() {
        return albumId;
    }

    public void setAlbumId(Long albumId) {
        this.albumId = albumId;
    }

    public Long getFileId() {
        return fileId;
    }

    public void setFileId(Long fileId) {
        this.fileId = fileId;
    }

    @Override
    public String toString() {
        return "Image [imageId=" + imageId + ", imageName=" + imageName + ", imageDescription=" + imageDescription
                + ", createdDate=" + createdDate + ", updatedDate=" + updatedDate + ", albumId=" + albumId + ", fileId="
                + fileId + "]";
    }  
    
} 