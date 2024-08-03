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
    @Query("SELECT n FROM Note n WHERE n.is_completed = :isCompleted")
    List<Note> findAllByIsCompleted(@Param("isCompleted") int isCompleted);
    @Query("SELECT n FROM Note n WHERE n.id_note = :id_note")
    Note getNoteByNoteId(@Param("id_note") Long id_note);
    @Query("SELECT n FROM Note n")
    List<Note> getNoteAllData();

    @Query("SELECT n FROM Note n WHERE n.is_completed = 2")
    List<Note> getNotesDone(@Param("isCompleted")int isCompleted);
    @Query("SELECT n FROM Note n WHERE n.is_completed = 1")
    List<Note> getNotesTodo(@Param("isCompleted")int isCompleted);

    @Query("DELETE FROM Note n WHERE n.is_completed = 0 OR n.is_completed = 1 OR n.is_completed = 2")
    @Modifying
    void deleteAllNotes();
    @Query("DELETE FROM Note n WHERE n.is_completed = 2")
    @Modifying
    void deleteAllDoneNotes();
}
