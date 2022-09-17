package com.udacity.jwdnd.course1.cloudstorage.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

import org.apache.ibatis.annotations.*;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;

@Mapper
public interface NoteMapper {

    @Delete("DELETE FROM NOTES WHERE noteId = #{noteId} AND userid = #{userId}")
    void delete(Note note);

    @Select("SELECT * FROM NOTES WHERE userid = #{userId} ORDER BY noteid DESC")
    List<Note> allFrom(Integer userId);

    @Update("UPDATE NOTES SET notetitle=#{noteTitle}, notedescription=#{noteDescription} WHERE noteid = #{noteId} AND userid = #{userId}")
    void update(Note note);

    @Insert("INSERT INTO NOTES (notetitle, notedescription, userid) VALUES(#{noteTitle}, #{noteDescription}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "noteId")
    int insert(Note note);
    
}
