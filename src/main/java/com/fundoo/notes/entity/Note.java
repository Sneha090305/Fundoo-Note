package com.fundoo.notes.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "notes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    // 🔥 NO relation — just userId
    private Long userId;

    private boolean isPinned = false;
    private boolean isArchived = false;
    private boolean isDeleted = false;
}