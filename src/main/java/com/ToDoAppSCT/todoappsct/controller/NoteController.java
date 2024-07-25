package com.ToDoAppSCT.todoappsct.controller;

import com.ToDoAppSCT.todoappsct.ApiResponse;
import com.ToDoAppSCT.todoappsct.DTO.request.CreateNoteRequestDTO;
import com.ToDoAppSCT.todoappsct.DTO.request.DeleteNoteRequestDTO;
import com.ToDoAppSCT.todoappsct.DTO.request.GetNotesRequestDTO;
import com.ToDoAppSCT.todoappsct.DTO.request.UpdateNoteRequestDTO;
import com.ToDoAppSCT.todoappsct.model.Note;
import com.ToDoAppSCT.todoappsct.service.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/")
public class NoteController {
    private final NoteService noteService;
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping(value = "v1/get-notes")
    public ResponseEntity<?> getNotes(@RequestBody GetNotesRequestDTO getNotesRequestDTO){
    /*    List<Note>noteList = new ArrayList<>();
        if (getNotesRequestDTO.getIs_completed() == null || getNotesRequestDTO.getIs_completed() == -1){
            noteList = noteService.getAllNotesByIdUser(getNotesRequestDTO.getId_user());
        }
        else{
            noteList = noteService.getAllNotesByIdUserAndIsCompleted(getNotesRequestDTO.getId_user(),getNotesRequestDTO.getIs_completed());
        }
        
        ApiResponse response = ApiResponse.builder().data(noteList).build();
        return ResponseEntity.ok(response);

    */



        return null;}

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping(value = "v1/add-note")
    public ResponseEntity<?> addNote(@RequestBody CreateNoteRequestDTO createNoteRequestDTO){
        Note newNote = Note.builder()
                .content(createNoteRequestDTO.getContent())
                .is_completed(createNoteRequestDTO.getIs_completed())
                .build();

        Note savedNote = noteService.save(newNote);
        ApiResponse response = ApiResponse.builder().data(savedNote).message("Note added successfully").build();
        return ResponseEntity.ok(response);
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
