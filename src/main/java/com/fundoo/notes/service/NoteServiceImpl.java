package com.fundoo.notes.service;

import com.fundoo.notes.dto.NoteRequest;
import com.fundoo.notes.entity.Note;
import com.fundoo.notes.entity.User;
import com.fundoo.notes.repository.NoteRepository;
import com.fundoo.notes.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.fundoo.notes.entity.Note;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NoteServiceImpl implements NoteService {

    private final NoteRepository noteRepository;
    private final UserRepository userRepository;

    @Override
    public String createNote(NoteRequest request, String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Note note = new Note();
        note.setTitle(request.getTitle());
        note.setDescription(request.getDescription());
        note.setUserId(user.getId());

        noteRepository.save(note);

        return "Note Created Successfully";
    }

    @Override
    public List<Note> getNotes(String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return noteRepository.findByUserId(user.getId());
    }

    @Override
    public String togglePin(Long noteId, String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Note note = noteRepository.findById(noteId)
                .orElseThrow(() -> new RuntimeException("Note not found"));

        if (!note.getUserId().equals(user.getId())) {
            throw new RuntimeException("Unauthorized");
        }

        note.setPinned(!note.isPinned());
        noteRepository.save(note);

        return "Pin status updated";
    }

    @Override
    public String toggleArchive(Long noteId, String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Note note = noteRepository.findById(noteId)
                .orElseThrow(() -> new RuntimeException("Note not found"));

        if (!note.getUserId().equals(user.getId())) {
            throw new RuntimeException("Unauthorized");
        }

        note.setArchived(!note.isArchived());
        noteRepository.save(note);

        return "Archive status updated";
    }

    @Override
    public String toggleTrash(Long noteId, String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Note note = noteRepository.findById(noteId)
                .orElseThrow(() -> new RuntimeException("Note not found"));

        if (!note.getUserId().equals(user.getId())) {
            throw new RuntimeException("Unauthorized");
        }

        note.setDeleted(!note.isDeleted());
        noteRepository.save(note);

        return "Trash status updated";
    }

}