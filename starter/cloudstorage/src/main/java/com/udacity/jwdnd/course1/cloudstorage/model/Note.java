package com.udacity.jwdnd.course1.cloudstorage.model;

public class Note {
    private Integer noteId;
    private String noteTitle;
    private Integer userId;
    private String noteDescription;

    public Note() {
    }

    public Note(Integer noteId, Integer userId) {
        this.noteId = noteId;
        this.userId = userId;
    }

    public Note(Integer noteId, String noteTitle, Integer userId, String noteDescription) {
        this.noteId = noteId;
        this.noteTitle = noteTitle;
        this.userId = userId;
        this.noteDescription = noteDescription;
    }

    public Integer getNoteId() {
        return this.noteId;
    }

    public void setNoteId(Integer noteId) {
        this.noteId = noteId;
    }

    public String getNoteTitle() {
        return this.noteTitle;
    }

    public void setNoteTitle(String noteTitle) {
        this.noteTitle = noteTitle;
    }

    public Integer getUserId() {
        return this.userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getNoteDescription() {
        return this.noteDescription;
    }

    public void setNoteDescription(String noteDescription) {
        this.noteDescription = noteDescription;
    }
    
}
