package com.ToDoAppSCT.todoappsct.repository;

import com.ToDoAppSCT.todoappsct.model.Note;
import com.ToDoAppSCT.todoappsct.model.ToDoUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface NoteRepository extends JpaRepository<Note, Long> {
    @Query("select n from Note n where n.id_user = :id_user")
    List<Note>getAllNotesByIdUser(@Param("id_user")Long id_user);

    @Query("select n from Note n where n.id_user = :id_user and n.is_completed = :is_completed")
    List<Note>getAllNotesByIdUserAndIsCompleted(@Param("id_user")Long id_user,@Param("is_completed")Integer is_completed);

    @Query("select n from Note n where n.id_user = :id_user and n.is_completed = 0")
    List<Note> getAllIncompleteNotesByIdUser(@Param("id_user") Long id_user);

    @Query("select n from Note n where n.id_user = :id_user and n.content like %:content%")
    List<Note> getAllNotesByIdUserAndContent(@Param("id_user") Long id_user, @Param("content") String content);

    @Query("select n from Note n where n.id_user = :id_user and n.id_note = :id_note")
    Optional<Note> findByIdUserAndIdNote(@Param("id_user") Long id_user, @Param("id_note") Long id_note);

    @Query("SELECT n FROM Note n WHERE n.is_completed = :isCompleted")
    List<Note> findAllByIsCompleted(@Param("isCompleted") int isCompleted);

    @Query("SELECT n FROM Note n WHERE n.id_note = :id_note")
    Note getNoteByNoteId(@Param("id_note") Long id_note);

    @Query("SELECT n FROM Note n")
    List<Note> getNoteAllData();

    @Query("DELETE FROM Note n WHERE n.is_completed = 0 OR n.is_completed = 1 OR n.is_completed = 2")
    @Modifying
    void deleteAllNotes();

    @Query("DELETE FROM Note n WHERE n.is_completed = 2")
    @Modifying
    void deleteAllDoneNotes();
}
