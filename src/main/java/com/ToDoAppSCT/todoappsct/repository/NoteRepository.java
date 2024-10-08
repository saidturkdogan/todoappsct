package com.ToDoAppSCT.todoappsct.repository;

import com.ToDoAppSCT.todoappsct.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NoteRepository extends JpaRepository<Note, Long> {
    @Query("SELECT n FROM Note n")
    List<Note> getNoteAllData();
    @Query("SELECT n FROM Note n WHERE n.is_completed = 2")
    List<Note> getNotesDone();
    @Query("SELECT n FROM Note n WHERE n.is_completed = 1")
    List<Note> getNotesTodo();
    @Query("DELETE FROM Note n WHERE n.is_completed = 0 OR n.is_completed = 1 OR n.is_completed = 2")
    @Modifying
    void deleteAllNotes();
    @Query("DELETE FROM Note n WHERE n.is_completed = 2")
    @Modifying
    void deleteAllDoneNotes();
}
