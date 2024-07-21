package com.ToDoAppSCT.todoappsct.controller;

import com.ToDoAppSCT.todoappsct.ApiResponse;
import com.ToDoAppSCT.todoappsct.DTO.request.GetNotesRequestDTO;
import com.ToDoAppSCT.todoappsct.model.Note;
import com.ToDoAppSCT.todoappsct.service.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/")
public class NoteController {
    private final NoteService noteService;
    @PostMapping(value = "v1/get-notes")
    public ResponseEntity<?> getNotes(@RequestBody GetNotesRequestDTO getNotesRequestDTO){
        List<Note>noteList = new ArrayList<>();
        if (getNotesRequestDTO.getIs_completed() == null || getNotesRequestDTO.getIs_completed() == -1){
            noteList = noteService.getAllNotesByIdUser(getNotesRequestDTO.getId_user());
        }
        else{
            noteList = noteService.getAllNotesByIdUserAndIsCompleted(getNotesRequestDTO.getId_user(),getNotesRequestDTO.getIs_completed());
        }

        ApiResponse response = ApiResponse.builder().data(noteList).build();
        return ResponseEntity.ok(response);
    }
}
