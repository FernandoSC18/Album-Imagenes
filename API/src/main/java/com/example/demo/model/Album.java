package com.example.demo.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "album")
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    @Column(name="album_id")
    private Long albumId;
 
    @Column(name="album_name")
    private String albumName;
 
    @Column(name="album_description")
    private String albumDescription;

    @Column(name="created_date")
    private Date createdDate;

    @Column(name="updated_date")
    private Date updatedDate;

    public Album(){  
    }

    public Album( String albumName, String albumDescription, Date createdDate, Date updatedDate ) {         
        this.albumName = albumName;
        this.albumDescription = albumDescription; 
        this.createdDate = createdDate; 
        this.updatedDate = updatedDate; 
    }

    public Long getAlbumId() {
        return albumId;
    }

    public void setAlbumId(Long albumId) {
        this.albumId = albumId;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public String getAlbumDescription() {
        return albumDescription;
    }

    public void setAlbumDescription(String albumDescription) {
        this.albumDescription = albumDescription;
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
    
    @Override
    public String toString() {
        return "Album [albumId=" + albumId + ", albumName=" + albumName 
            + ", albumDescription=" + albumDescription + ", createdDate=" + createdDate + ", updatedDate=" + updatedDate
            + "]";
    }
 

}
