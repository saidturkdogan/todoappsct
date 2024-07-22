package com.ToDoAppSCT.todoappsct.DTO.request;

import lombok.Data;

@Data
public class NoteUpdateRequestDTO {
    private Long id_user;
    private Long id_note;
    private String content;
    private Integer is_completed;

}
