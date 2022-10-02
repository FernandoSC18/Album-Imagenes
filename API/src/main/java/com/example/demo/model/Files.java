package com.example.demo.model;
 
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table; 
 
@Entity
@Table(name = "files")
public class Files {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    @Column(name="file_id")
    private Long fileId;
 
    @Column(name="file_name")
    private String fileName;
 
    @Column(name="file_description")
    private String fileDescription;

    @Column(name="file_type")
    private String fileType;
    
    @Column(name="file_mime_type")
    private String fileMimeType;

    @Column(name="file_size")
    private int fileSize;

    @Column(name="file_data")
    private String fileData;

    @Column(name="created_date")
    private Date createdDate;

    @Column(name="updated_date")
    private Date updatedDate;

    public Files(){  
    }

    public Files( String fileName, String fileDescription, String fileType, String fileMimeType, int fileSize, String fileData
    , Date createdDate, Date updatedDate) {         
        this.fileName = fileName;
        this.fileDescription = fileDescription; 
        this.fileType = fileType; 
        this.fileMimeType = fileMimeType; 
        this.fileSize = fileSize; 
        this.fileData = fileData; 
        this.createdDate = createdDate; 
        this.updatedDate = updatedDate; 
    }

    

    public Long getFileId() {
        return fileId;
    }

    public void setFileId(Long fileId) {
        this.fileId = fileId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileDescription() {
        return fileDescription;
    }

    public void setFileDescription(String fileDescription) {
        this.fileDescription = fileDescription;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getFileMimeType() {
        return fileMimeType;
    }

    public void setFileMimeType(String fileMimeType) {
        this.fileMimeType = fileMimeType;
    }

    public int getFileSize() {
        return fileSize;
    }

    public void setFileSize(int fileSize) {
        this.fileSize = fileSize;
    }

    public String getFileData() {
        return fileData;
    }

    public void setFileData(String fileData) {
        this.fileData = fileData;
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
        return "Files [fileId=" + fileId + ", fileName=" + fileName + ", fileDescription=" + fileDescription
                + ", fileType=" + fileType + ", fileMimeType=" + fileMimeType + ", fileSize=" + fileSize + ", fileData="
                + fileData + ", createdDate=" + createdDate + ", updatedDate=" + updatedDate + "]";
    }
 
} 