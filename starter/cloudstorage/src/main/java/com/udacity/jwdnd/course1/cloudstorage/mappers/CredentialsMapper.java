package com.udacity.jwdnd.course1.cloudstorage.mappers;

import com.udacity.jwdnd.course1.cloudstorage.models.Credential;
import com.udacity.jwdnd.course1.cloudstorage.models.Note;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
@Mapper
public interface CredentialsMapper {
    @Select("SELECT * FROM Credentials WHERE credentialid = #{credentialId}")
    Credential getCredential(Integer credentialId);

    @Select("SELECT * FROM Credentials WHERE userid = #{userId}")
    List<Credential> getAllCredentials(Integer userId);

    @Insert("INSERT INTO Credentials (url, username, key, password, userid) VALUES(#{url}, #{username}, #{key}, #{password}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "credentialId")
    void insertCredential(Credential credential);

    @Update("UPDATE Credentials SET url=#{url}, username=#{username}, key=#{key}, password=#{password}, userid=#{userId} WHERE credentialid = #{credentialId}")
    void updateCredential(String url, String username, String key, String password, Integer userId, Integer credentialId);

    @Delete("DELETE FROM Credentials WHERE credentialid = #{credentialId}")
    void deleteCredential(Integer id);
}
