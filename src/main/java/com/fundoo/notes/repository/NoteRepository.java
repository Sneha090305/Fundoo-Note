package com.fundoo.notes.repository;

import com.fundoo.notes.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface NoteRepository extends JpaRepository<Note, Long> {

    List<Note> findByUserId(Long userId);

    List<Note> findByUserIdAndDeletedFalse(Long userId);

    List<Note> findByUserIdAndPinnedTrue(Long userId);

    List<Note> findByUserIdAndArchivedTrue(Long userId);

    List<Note> findByUserIdAndDeletedTrue(Long userId);

    List<Note> findByUserIdAndTitleContainingIgnoreCaseAndDeletedFalse(Long userId, String title);

    Page<Note> findByUserIdAndDeletedFalse(Long userId, Pageable pageable);
}