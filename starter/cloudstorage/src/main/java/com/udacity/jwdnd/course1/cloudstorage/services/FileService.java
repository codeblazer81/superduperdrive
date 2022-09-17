package com.udacity.jwdnd.course1.cloudstorage.services;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.List;

import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.User;

@Service
public class FileService {

    private FileMapper fileMapper;
    private UserMapper userMapper;


    @Autowired
    public FileService(FileMapper fileMapper, UserMapper userMapper){
        this.fileMapper = fileMapper;
        this.userMapper = userMapper;
      
    }

    public File getFiles(){

        return fileMapper.getFiles();

    }

    public File getFile(File file){

        return fileMapper.get(file);

    }
    
    public int uploadFile(MultipartFile file) throws SerialException, SQLException, IOException{

        File uploadFile = new File();
      
        uploadFile.setFileName(file.getOriginalFilename()); 
        uploadFile.setContentType(file.getContentType());
        uploadFile.setFileSize(file.getSize());
        uploadFile.setUserId(userMapper.getUser().getUserId());

        uploadFile.setFileData(file.getBytes());

        return fileMapper.insert(uploadFile);

    }

    public List<File> allFrom (Integer userId){

        return fileMapper.allFrom(userId);
    }

    public void removeFile(File file){
        fileMapper.delete(file);
    }

    public boolean fileExists(File file){
        return fileMapper.get(file) != null;
    }

}
