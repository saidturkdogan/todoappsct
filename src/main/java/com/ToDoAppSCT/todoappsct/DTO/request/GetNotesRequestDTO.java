package com.ToDoAppSCT.todoappsct.DTO.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class GetNotesRequestDTO {
    private Long id_user;
    private Integer is_completed;
}
