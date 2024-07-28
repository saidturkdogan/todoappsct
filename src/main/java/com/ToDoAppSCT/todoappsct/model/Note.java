package com.ToDoAppSCT.todoappsct.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Getter
@Setter
@Table(name = "note")

public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_note",nullable = false)
    private Long id_note;
    @Basic
    @Column(name = "content")
    private String content;
    @Basic
    @Column(name = "is_completed")
    private Integer is_completed;
    @Basic
    @Column(name = "id_user")
    private Long id_user;



}
