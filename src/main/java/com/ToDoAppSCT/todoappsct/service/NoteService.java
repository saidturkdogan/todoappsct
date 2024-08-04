package com.ToDoAppSCT.todoappsct.service;

import com.ToDoAppSCT.todoappsct.DTO.request.UpdateNoteRequestDTO;
import com.ToDoAppSCT.todoappsct.model.Note;
import com.ToDoAppSCT.todoappsct.repository.NoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;


@Service
@RequiredArgsConstructor
public class NoteService {

    private final NoteRepository noteRepository;

    public List<Note> getNoteAllNote() {
        return noteRepository.getNoteAllData();
    }
    public List<Note> getDoneNotes(){
        return noteRepository.getNotesDone();
    }
    public List<Note> getTodoNotes(){
        return noteRepository.getNotesTodo();
    }
    public Note save(Note note)
    {
        return noteRepository.save(note);
    }
    public void deleteNoteById(Long id) {
        noteRepository.deleteById(id);
    }
    public Note updateNote(UpdateNoteRequestDTO updateNoteRequestDTO) {
        if (updateNoteRequestDTO.getId_note() == null) {
            throw new IllegalArgumentException("The given id must not be null");
        }

        Note existingNote = noteRepository.findById(updateNoteRequestDTO.getId_note())
                .orElseThrow(() -> new ResourceNotFoundException("Note not found with id: " + updateNoteRequestDTO.getId_note()));
        existingNote.setContent(updateNoteRequestDTO.getContent());
        existingNote.setIs_completed(updateNoteRequestDTO.getIs_completed());
        existingNote.setId_user(updateNoteRequestDTO.getId_user() != null ? updateNoteRequestDTO.getId_user() : 1);
        return noteRepository.save(existingNote);
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
