package com.ToDoAppSCT.todoappsct.controller;

import com.ToDoAppSCT.todoappsct.ApiResponse;
import com.ToDoAppSCT.todoappsct.DTO.request.*;
import com.ToDoAppSCT.todoappsct.model.Note;
import com.ToDoAppSCT.todoappsct.service.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/")
public class NoteController {
    private final NoteService noteService;

    @CrossOrigin(origins = "http://localhost:5173")
    @GetMapping(value = "v1/get-notesalldata")
    public List<Note> getNotesAllData() {
        return noteService.getNoteAllNote();
    }

    @CrossOrigin(origins = "http://localhost:5173")
    @GetMapping(value = "v1/get-donenotes")
    public List<Note> getDoneNotes() {
        return noteService.getDoneNotes();
    }

    @CrossOrigin(origins = "http://localhost:5173")
    @GetMapping(value = "v1/get-todonotes")
    public List<Note> getTodoNotes() {
        return noteService.getTodoNotes();
    }




    @CrossOrigin(origins = "http://localhost:5173")
    @PostMapping(value = "v1/add-note")
    public ResponseEntity<?> addNote(@RequestBody CreateNoteRequestDTO createNoteRequestDTO) {
        Note newNote = Note.builder()
                .content(createNoteRequestDTO.getContent())
                .is_completed(1)
                .id_user(1L)
                .build();
        Note savedNote = noteService.save(newNote);
        ApiResponse response = ApiResponse.builder().data(savedNote).message("Note added successfully").build();
        return ResponseEntity.ok(response);
    }

    @CrossOrigin(origins = "http://localhost:5173")
    @PutMapping(value = "v1/update-note")
    public ResponseEntity<Note> updateNote(@RequestBody UpdateNoteRequestDTO updateNoteRequestDTO) {
        System.out.println("Received NoteDto: " + updateNoteRequestDTO);
        Note updatedNote = noteService.updateNote(updateNoteRequestDTO);
        return ResponseEntity.ok(updatedNote);
    }

    @CrossOrigin(origins = "http://localhost:5173")
    @DeleteMapping("/v1/delete-note")
    public ResponseEntity<?> deleteNote(@RequestBody Map<String, Long> payload) {
        Long idNote = payload.get("id_note");
        noteService.deleteNoteById(idNote);
        return ResponseEntity.ok().build();
    }

    @CrossOrigin(origins = "http://localhost:5173")
    @DeleteMapping(value = "v1/delete-all-notes")
    public ResponseEntity<Void> deleteAllNotes() {
        noteService.deleteAllNotes();
        return ResponseEntity.noContent().build();
    }

    @CrossOrigin(origins = "http://localhost:5173")
    @DeleteMapping(value = "v1/delete-all-done-notes")
    public ResponseEntity<Void> deleteAllDoneNotes() {
        noteService.deleteAllDoneNotes();
        return ResponseEntity.noContent().build();
    }
}
