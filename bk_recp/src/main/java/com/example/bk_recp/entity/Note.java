package com.example.bk_recp.entity;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Date;

@Data
@AllArgsConstructor
public class Note {
    private Long id_notes;
    private String title_note;
    private String description;
    private Date date_cr;
}
