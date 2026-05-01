package com.fundoo.notes.controller;

import com.fundoo.notes.dto.NoteRequest;
import com.fundoo.notes.dto.UpdateNoteRequest;
import com.fundoo.notes.entity.Note;
import com.fundoo.notes.service.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/notes")
public class NoteController {

    private final NoteService service;

    @PostMapping
    public String createNote(@RequestBody NoteRequest request,
                             Authentication authentication) {

        return service.createNote(request, authentication.getName());
    }

    @GetMapping
    public List<Note> getNotes(Authentication authentication) {

        return service.getNotes(authentication.getName());
    }

    @PutMapping("/pin/{id}")
    public String togglePin(@PathVariable Long id, Authentication auth) {

        return service.togglePin(id, auth.getName());
    }

    @PutMapping("/archive/{id}")
    public String toggleArchive(@PathVariable Long id, Authentication auth) {

        return service.toggleArchive(id, auth.getName());
    }

    @PutMapping("/trash/{id}")
    public String toggleTrash(@PathVariable Long id, Authentication auth) {

        return service.toggleTrash(id, auth.getName());
    }

    @PutMapping("/restore/{id}")
    public String restoreNote(@PathVariable Long id, Authentication auth) {

        return service.restoreNote(id, auth.getName());
    }

    @DeleteMapping("/{id}")
    public String deleteNote(@PathVariable Long id, Authentication auth) {

        return service.deleteNotePermanently(id, auth.getName());
    }


    @PutMapping("/{id}")
    public Note updateNote(@PathVariable Long id,
                           @RequestBody UpdateNoteRequest request,
                           Authentication authentication) {

        return service.updateNote(id, authentication.getName(), request);
    }


    @GetMapping("/search")
    public List<Note> searchNotes(@RequestParam String q,
                                  Authentication authentication) {

        return service.searchNotes(authentication.getName(), q);
    }

    @GetMapping("/pinned")
    public List<Note> getPinnedNotes(Authentication auth) {
        return service.getPinnedNotes(auth.getName());
    }

    @GetMapping("/archived")
    public List<Note> getArchivedNotes(Authentication auth) {
        return service.getArchivedNotes(auth.getName());
    }

    @GetMapping("/trash")
    public List<Note> getTrashedNotes(Authentication auth) {
        return service.getTrashedNotes(auth.getName());
    }
}