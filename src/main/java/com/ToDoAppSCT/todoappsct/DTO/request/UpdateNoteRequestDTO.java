package com.ToDoAppSCT.todoappsct.DTO.request;

import lombok.Data;

@Data
public class UpdateNoteRequestDTO {

    private Long id_note;
    private String content;
    private Integer is_completed;

}
