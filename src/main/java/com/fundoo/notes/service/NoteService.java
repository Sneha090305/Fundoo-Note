package com.fundoo.notes.service;

import com.fundoo.notes.dto.NoteRequest;
import com.fundoo.notes.entity.Note;
import java.util.List;

public interface NoteService {

    String createNote(NoteRequest request, String email);

    List<Note> getNotes(String email);

    String togglePin(Long noteId, String email);
    String toggleArchive(Long noteId, String email);
    String toggleTrash(Long noteId, String email);
    String restoreNote(Long noteId, String email);
    String deleteNotePermanently(Long noteId, String email);
}