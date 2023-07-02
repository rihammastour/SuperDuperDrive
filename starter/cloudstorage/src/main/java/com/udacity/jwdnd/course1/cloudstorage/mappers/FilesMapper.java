package com.udacity.jwdnd.course1.cloudstorage.mappers;

import com.udacity.jwdnd.course1.cloudstorage.models.File;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface FilesMapper {
    @Select("SELECT * FROM Files WHERE fileId = #{fileId}")
    File getFile(Integer fileId);

    @Select("SELECT * FROM Files WHERE userId = #{userId}")
    List<File> getAllFiles(Integer userId);

    @Insert("INSERT INTO Files (filename, contenttype, filesize, userid, filedata) VALUES(#{fileName}, #{contentType}, #{fileSize}, #{userId}, #{fileData})")
    @Options(useGeneratedKeys = true, keyProperty = "fileId")
    void insertFile(File file);

    @Delete("DELETE FROM Files WHERE fileId = #{fileId}")
    void deleteFile(Integer id);
}
