package com.udacity.jwdnd.course1.cloudstorage.mappers;

import com.udacity.jwdnd.course1.cloudstorage.models.Note;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
@Mapper
public interface NotesMapper {
    @Select("SELECT * FROM Notes WHERE noteId = #{noteId}")
    Note getNote(Integer noteId);

    @Select("SELECT * FROM Notes WHERE userid = #{userId}")
    List<Note> getAllNotes(Integer userId);

    @Insert("INSERT INTO Notes (notetitle, notedescription, userid) VALUES(#{noteTitle}, #{noteDescription}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "noteId")
    void insertNote(Note note);

    @Update("UPDATE Notes SET notetitle=#{noteTitle}, notedescription=#{noteDescription}, userid=#{userId} WHERE noteid=#{noteId}")
    void updateNote(String noteTitle, String noteDescription, Integer userId, Integer noteId);

    @Delete("DELETE FROM Notes WHERE noteid = #{noteId}")
    void deleteNote(Integer id);
}
