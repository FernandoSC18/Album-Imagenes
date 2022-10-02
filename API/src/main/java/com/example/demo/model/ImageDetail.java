package com.example.demo.model;
 
import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity; 
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table; 
 
/*
* ImageDetail is an more detailed version of Imaga Entity, but both request to the same table
* The only difference is Master detail by foregn keys, this class have it, and Image Class only have Ids
*/
@Entity
@Table(name = "image")
public class ImageDetail {

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

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "album_id")
    private Album album; 
 
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "file_id")
    private Files file;  

    public ImageDetail( ) {  
    } 

    public ImageDetail( String imageName, String imageDescription, Date createdDate, Date updatedDate,
        Album album, Files file) { 
        this.imageName = imageName;
        this.imageDescription = imageDescription;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.album = album;
        this.file = file;
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

    public Album getAlbum () {
        return album;
    }

    public void setAlbum (Album album) {
        this.album = album;
    }
    
    public Files getFile () {
        return file;
    }

    public void setFile (Files file) {
        this.file = file;
    }

    @Override
    public String toString() {
        return "Image [imageId=" + imageId + ", imageName=" + imageName + ", imageDescription=" + imageDescription
                + ", createdDate=" + createdDate + ", updatedDate=" + updatedDate + ", albumId=" + album + ", fileId="
                + file + "]";
    }  
    
} 