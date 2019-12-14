package uk.ac.gcu.notes.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uk.ac.gcu.notes.model.request.NoteRequest;
import uk.ac.gcu.notes.service.NoteService;

@RestController
@RequestMapping(path = "/notes")
public class NoteController {
  private NoteService noteService;

  public NoteController(NoteService noteService) {
    this.noteService = noteService;
  }

  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> create(@RequestBody NoteRequest request) {
    return new ResponseEntity<>(noteService.create(request), HttpStatus.CREATED);
  }

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> getNotes() {
    return new ResponseEntity<>(noteService.getNotes(), HttpStatus.OK);
  }

  @PutMapping(path = "/{noteId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> update(@PathVariable String  noteId, @RequestBody NoteRequest request) {
    return new ResponseEntity<>(noteService.update(noteId, request), HttpStatus.OK);
  }

  @DeleteMapping(path = "/{noteId}")
  public ResponseEntity<?> delete(@PathVariable String noteId) {
    noteService.delete(noteId);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
