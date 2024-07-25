package com.ToDoAppSCT.todoappsct.controller;

import com.ToDoAppSCT.todoappsct.ApiResponse;
import com.ToDoAppSCT.todoappsct.DTO.request.*;
import com.ToDoAppSCT.todoappsct.model.Note;
import com.ToDoAppSCT.todoappsct.service.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.Node;

import java.util.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/")
public class NoteController {
    private final NoteService noteService;
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping(value = "v1/get-notes")
    public ResponseEntity<?> getNotes(@RequestBody GetNotesRequestDTO getNotesRequestDTO)
    {
        List<Note>noteList = new ArrayList<>();
    /*
        if (getNotesRequestDTO.getIs_completed() == null || getNotesRequestDTO.getIs_completed() == -1){
            noteList = noteService.getAllNotesByIdUser(getNotesRequestDTO.getId_user());
        }
        else{
            noteList = noteService.getAllNotesByIdUserAndIsCompleted(getNotesRequestDTO.getId_user(),getNotesRequestDTO.getIs_completed());
        }
        
        ApiResponse response = ApiResponse.builder().data(noteList).build();
        return ResponseEntity.ok(response);

    */
        if (getNotesRequestDTO.getIs_completed() == null || getNotesRequestDTO.getIs_completed() == -1){
            noteList = noteService.getAllNotes();
        }
        else {
            noteList = noteService.getAllNotesByIsCompleted();
        }


        return null;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping(value = "v1/get-notesbyid")
    public Note getNotesbyNoteid(@RequestBody UpdateNoteRequestDTO updateNoteRequestDTO)
    {
        var asd=33;

        var id=Integer.parseInt(updateNoteRequestDTO.getId_note().toString());

        Note  note = noteService.getNotebyId(id);

        return note;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping(value = "v1/get-notesAlldata")
    public List<Note> getNotesAllData()
    {

        return noteService.getNoteAllNote();
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping(value = "v1/add-note")
    public ResponseEntity<?> addNote(@RequestBody CreateNoteRequestDTO createNoteRequestDTO)
    {

        Note newNote = Note.builder()
                .content(createNoteRequestDTO.getContent())
                .is_completed(createNoteRequestDTO.getIs_completed())
                .build();

        Note savedNote = noteService.save(newNote);

        ApiResponse response = ApiResponse.builder().data(savedNote).message("Note added successfully").build();

        return ResponseEntity.ok(response);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping(value = "v1/findAllByIsCompleted")
    public List<Note> findAllByIsCompletedNote(@RequestBody GetIsCompletedNotes getIsCompletedNotes) {

        if (Integer.parseInt(getIsCompletedNotes.getIs_completed().toString()) == 1) {
           return noteService.findAllByIsCompleted(Integer.parseInt(getIsCompletedNotes.getIs_completed().toString()));
        }
        else if (Integer.parseInt(getIsCompletedNotes.getIs_completed().toString()) == 2) {
            return noteService.findAllByIsCompleted(Integer.parseInt(getIsCompletedNotes.getIs_completed().toString()));
        }
        else {
            return noteService.getNoteAllNote();
        }
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PutMapping(value = "v1/update-note")
    public ResponseEntity<?> updateNote(@RequestBody UpdateNoteRequestDTO updateNoteRequestDTO) {
        Optional<Note> optionalNote = noteService.findById(updateNoteRequestDTO.getId_note());
        return null;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @DeleteMapping(value = "v1/delete-note")
    public ResponseEntity<?> deleteNote(@RequestBody DeleteNoteRequestDTO deleteNoteRequestDTO) {
        return null;

    }
}
