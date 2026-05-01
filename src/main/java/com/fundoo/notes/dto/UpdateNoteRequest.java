package com.fundoo.notes.dto;

import lombok.Data;

@Data
public class UpdateNoteRequest {
    private String title;
    private String description;
}