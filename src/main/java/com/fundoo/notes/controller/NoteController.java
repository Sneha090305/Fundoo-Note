package com.fundoo.notes.controller;

import com.fundoo.notes.dto.NoteRequest;
import com.fundoo.notes.entity.Note;
import com.fundoo.notes.service.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notes")
@RequiredArgsConstructor
public class NoteController {

    private final NoteService service;

    @PostMapping
    public String createNote(@RequestBody NoteRequest request,
                             Authentication authentication) {

        String email = authentication.getName();

        return service.createNote(request, email);
    }

    @GetMapping
    public List<Note> getNotes(Authentication authentication) {

        String email = authentication.getName(); // from JWT

        return service.getNotes(email);
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
}