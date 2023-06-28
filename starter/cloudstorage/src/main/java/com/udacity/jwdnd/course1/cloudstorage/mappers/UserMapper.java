package com.udacity.jwdnd.course1.cloudstorage.mappers;

import com.udacity.jwdnd.course1.cloudstorage.models.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface UserMapper {
    @Select("SELECT * FROM Users WHERE username = #{username}")
    User getUser(String username);

    @Insert("INSERT INTO Users (username, salt, password, firstname, lastname) VALUES(#{username}, #{salt}, #{password}, #{firstname}, #{lastname})")
    @Options(useGeneratedKeys = true, keyProperty = "userId")
    Integer insert(User user);


    @Delete("DELETE FROM Users WHERE id = #{id}")
    void delete(Integer id);
}

