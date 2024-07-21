package com.ToDoAppSCT.todoappsct.repository;

import com.ToDoAppSCT.todoappsct.model.Note;
import com.ToDoAppSCT.todoappsct.model.ToDoUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NoteRepository extends JpaRepository<Note, Long> {
    @Query("select n from Note n where n.id_user = :id_user")
    List<Note>getAllNotesByIdUser(@Param("id_user")Long id_user);

    @Query("select n from Note n where n.id_user = :id_user and n.is_completed = :is_completed")
    List<Note>getAllNotesByIdUserAndIsCompleted(@Param("id_user")Long id_user,@Param("is_completed")Integer is_completed);


}
