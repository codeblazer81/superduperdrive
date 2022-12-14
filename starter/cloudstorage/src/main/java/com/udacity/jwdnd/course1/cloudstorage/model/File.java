package com.udacity.jwdnd.course1.cloudstorage.model;

import java.sql.Blob;

public class File {

    private Integer fileId;
    private String fileName;
    private String contentType;
    private long fileSize;
    private Integer userId;
    private byte[] fileData;


    public File(Integer fileId, Integer userId) {
        this.fileId = fileId;
        this.userId = userId;
    }

    public File(Integer fileId, String fileName, String contentType, long fileSize, Integer userId, byte[] fileData) {
        this.fileId = fileId;
        this.fileName = fileName;
        this.contentType = contentType;
        this.fileSize = fileSize;
        this.userId = userId;
        this.fileData = fileData;
    }

    public File(){
        this.fileId = null;
        this.fileName = null;
        this.contentType = null;
        this.fileSize = 0;
        this.userId = null;
        this.fileData = null;
    }

    public Integer getFileId() {
        return this.fileId;
    }

    public void setFileId(Integer fileId) {
        this.fileId = fileId;
    }

    public String getFileName() {
        return this.fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getContentType() {
        return this.contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public long getFileSize() {
        return this.fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public Integer getUserId() {
        return this.userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public byte[] getFileData() {
        return this.fileData;
    }

    public void setFileData(byte[] fileData) {
        this.fileData = fileData;
    }
    
}