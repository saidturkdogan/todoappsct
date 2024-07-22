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
    public List<Note>getAllNotesByIdUser(Long id_user){
        return noteRepository.getAllNotesByIdUser(id_user);
    }

    public List<Note>getAllNotesByIdUserAndIsCompleted(Long id_user, Integer is_completed){
        return noteRepository.getAllNotesByIdUserAndIsCompleted(id_user,is_completed);
    }

    public Optional<Note> findByIdUserAndIdNote(Long id_user, Long id_note) {
        return noteRepository.findByIdUserAndIdNote(id_user, id_note);
    }

    public Note save(Note note) {
        return noteRepository.save(note);
    }

    public void deleteById(Long id) {
        noteRepository.deleteById(id);
    }

    public Note update(Note note) {
        return noteRepository.save(note);
    }

}
