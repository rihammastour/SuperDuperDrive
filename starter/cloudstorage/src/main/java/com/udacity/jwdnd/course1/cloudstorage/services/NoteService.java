package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mappers.NotesMapper;
import com.udacity.jwdnd.course1.cloudstorage.models.Note;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {
    private final NotesMapper notesMapper;

    public NoteService(NotesMapper notesMapper) {
        this.notesMapper = notesMapper;
    }

    public Note getNote(Integer noteId){
        return this.notesMapper.getNote(noteId);
    }

    public List<Note> getAllNotes(Integer userId){
        return this.notesMapper.getAllNotes(userId);
    }

    public void insertNote(Note note){
      this.notesMapper.insertNote(note);
    }

    public void updateNote(Note note){
        this.notesMapper.updateNote(note.getNoteTitle(), note.getNoteDescription(), note.getUserId(), note.getNoteId());
    }

    public void deleteNote(Integer noteId){
        this.notesMapper.deleteNote(noteId);
    }

}
