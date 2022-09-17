package com.udacity.jwdnd.course1.cloudstorage.mapper;

import java.util.List;
import org.apache.ibatis.annotations.*;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;

@Mapper
public interface CredentialMapper {

    @Delete("DELETE FROM CREDENTIALS WHERE credentialId = #{credentialId} AND userid = #{userId}")
    void delete(Credential credential);

    @Select("SELECT credentialId, url, password, username, userid FROM CREDENTIALS WHERE userid = #{userId} ORDER BY credentialId DESC")
    List<Credential> allFrom(Integer userId);

    @Select("SELECT credentialId, userkey, url, password, username, userid FROM CREDENTIALS WHERE credentialId = #{credentialId} AND userid = #{userId}")
    Credential find(Credential credential);

    @Update("UPDATE CREDENTIALS SET url=#{url}, username=#{username}, password=#{password} WHERE credentialId =#{credentialId}")
    void update(Credential credential);

    @Insert("INSERT INTO CREDENTIALS ( url, userkey, username, password, userid) VALUES(#{url}, #{userkey}, #{username}, #{password}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "credentialId")
    int insert(Credential credential);

}
