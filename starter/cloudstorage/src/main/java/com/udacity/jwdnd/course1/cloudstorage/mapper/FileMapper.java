package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.File;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface FileMapper {
    @Select("SELECT * FROM FILES")
    File getFiles();

    @Insert("INSERT INTO FILES (filename, contenttype, filesize, userid, filedata) VALUES(#{fileName}, #{contentType}, #{fileSize}, #{userId}, #{fileData})")
    @Options(useGeneratedKeys = true, keyProperty = "fileId")
    Integer insert(File file);

    @Delete("DELETE FROM FILES WHERE fileId = #{fileId} AND userid = #{userId}")
    void delete(File file);

    @Select("SELECT fileId, filename, contenttype, filesize, filedata, userid FROM FILES WHERE fileId = #{fileId} AND userid = #{userId}")
    @Results({
        @Result(property = "contentType", column = "contenttype")
    })
    File get(File file);

    @Select("SELECT * FROM FILES WHERE userid = #{userId} AND fileid = #{fileId}")
    File find(File file);
    
    @Select("SELECT fileId, fileName FROM FILES WHERE userid = #{userId} ORDER BY fileId DESC")
    List<File> allFrom(Integer userId);
}

