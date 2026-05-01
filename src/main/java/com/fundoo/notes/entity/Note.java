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

    private Long userId;


    private boolean deleted = false;

    private boolean pinned = false;
    private boolean archived = false;
}