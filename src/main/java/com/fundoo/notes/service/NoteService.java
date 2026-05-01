package com.fundoo.notes.service;

import com.fundoo.notes.dto.NoteRequest;
import com.fundoo.notes.dto.UpdateNoteRequest;
import com.fundoo.notes.entity.Note;
import org.springframework.data.domain.Page;

import java.util.List;

public interface NoteService {

    String createNote(NoteRequest request, String email);

    List<Note> getNotes(String email);

    String togglePin(Long noteId, String email);
    String toggleArchive(Long noteId, String email);
    String toggleTrash(Long noteId, String email);
    String restoreNote(Long noteId, String email);
    String deleteNotePermanently(Long noteId, String email);


    Note updateNote(Long noteId, String email, UpdateNoteRequest request);

    List<Note> searchNotes(String email, String query);

    List<Note> getPinnedNotes(String email);

    List<Note> getArchivedNotes(String email);

    List<Note> getTrashedNotes(String email);

    Page<Note> getNotesPaginated(String email, int page, int size, String sortBy, String direction);
}