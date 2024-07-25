package com.ToDoAppSCT.todoappsct.DTO.request;


import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class CreateNoteRequestDTO {

    private String content;
    private Integer is_completed;


}
