package uk.ac.gcu.notes.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uk.ac.gcu.notes.entity.Note;
import uk.ac.gcu.notes.model.request.NoteRequest;
import uk.ac.gcu.notes.service.NoteService;

import java.util.List;

@RestController
@RequestMapping(path = "/notes")
public class NoteController {
  private NoteService noteService;

  public NoteController(NoteService noteService) {
    this.noteService = noteService;
  }

  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public Note create(@RequestBody NoteRequest request) {
    return noteService.create(request);
  }

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public List<Note> getNotes() {
    return noteService.getNotes();
  }
}
