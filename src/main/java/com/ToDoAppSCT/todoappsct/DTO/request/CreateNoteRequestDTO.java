package com.ToDoAppSCT.todoappsct.DTO.request;


import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class CreateNoteRequestDTO {
    private Long id_user = 1L;
    private String content;
    private Integer is_completed;


}
