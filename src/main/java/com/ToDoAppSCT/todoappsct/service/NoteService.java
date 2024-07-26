package com.ToDoAppSCT.todoappsct.service;

import com.ToDoAppSCT.todoappsct.model.Note;
import com.ToDoAppSCT.todoappsct.repository.NoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.naming.LinkLoopException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NoteService {

    private final NoteRepository noteRepository;
    /*public List<Note>getAllNotesByIdUser(Long id_user){
        return noteRepository.getAllNotesByIdUser(id_user);
    }

    public List<Note>getAllNotesByIdUserAndIsCompleted(Long id_user, Integer is_completed){
        return noteRepository.getAllNotesByIdUserAndIsCompleted(id_user,is_completed);
    }*/

    public List<Note> getAllNotes() {
        return noteRepository.findAll();
    }

    public Note getNotebyId(Long noteid) {
        return noteRepository.getNoteByNoteId(noteid);
    }


    public List<Note> getNoteAllNote() {
        return noteRepository.getNoteAllData();
    }

    public List<Note> findAllByIsCompleted(int isCompleted) {
        return noteRepository.findAllByIsCompleted(isCompleted);
    }


    public List<Note> getAllNotesByIsCompleted() {
        return null ;//noteRepository.findAllByIsCompleted();
    }



    public Optional<Note> findById(Long id) {

        return noteRepository.findById(id);
    }

    public Note save(Note note)
    {
        return noteRepository.save(note);
    }


    public void deleteNoteById(Long id) {
        noteRepository.deleteById(id);
    }

    public Note getNoteByNoteId(Long id_note) {
        return noteRepository.getNoteByNoteId(id_note);
    }

    public Note updateNoteById(Long id_note, String content, int is_completed) {
        Note note = noteRepository.getNoteByNoteId(id_note);
        if (note != null) {
            note.setContent(content);
            note.setIs_completed(is_completed);
            noteRepository.save(note);
        }
        return note;
    }

    @Transactional
    public void deleteAllNotes() {
        noteRepository.deleteAllNotes();
    }

    @Transactional
    public void deleteAllDoneNotes() {
        noteRepository.deleteAllDoneNotes();
    }
}
