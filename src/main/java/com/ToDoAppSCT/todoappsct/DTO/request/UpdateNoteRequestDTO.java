package com.ToDoAppSCT.todoappsct.DTO.request;

import lombok.Data;

@Data
public class UpdateNoteRequestDTO {
    private Long id_note;
    private String content;
    private Integer is_completed;
    private Long id_user;

    // Getters and setters

    @Override
    public String toString() {
        return "UpdateNoteRequestDTO{" +
                "id_note=" + id_note +
                ", content='" + content + '\'' +
                ", is_completed=" + is_completed +
                ", id_user=" + id_user +
                '}';
    }
}
