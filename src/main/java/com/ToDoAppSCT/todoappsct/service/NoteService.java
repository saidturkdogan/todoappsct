package com.ToDoAppSCT.todoappsct.service;

import com.ToDoAppSCT.todoappsct.model.Note;
import com.ToDoAppSCT.todoappsct.repository.NoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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

    public Note getNotebyId(int noteid) {
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


    public void delete(Note existingNote) {
        noteRepository.delete(existingNote);
    }
}
