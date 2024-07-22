package com.ToDoAppSCT.todoappsct.DTO.request;


import lombok.Data;

@Data
public class CreateNoteRequestDTO {
    private String content;
    private Integer is_completed;
    private Long id_user;

}
