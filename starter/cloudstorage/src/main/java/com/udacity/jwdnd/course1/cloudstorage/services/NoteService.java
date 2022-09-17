package com.udacity.jwdnd.course1.cloudstorage.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.udacity.jwdnd.course1.cloudstorage.mapper.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;

@Service
public class NoteService {
    
    private final NoteMapper notes;

    public NoteService(NoteMapper mapper) {
        this.notes = mapper;
    }

    public List<Note> allBy(Integer userId) {
        return notes.allFrom(userId);
    }

    public void remove(Note note) {
        notes.delete(note);
    }

    public void add(Note note) {
        if (note.getNoteId() == null) {
            notes.insert(note);
            return;
        }

        notes.update(note);
    }
}
