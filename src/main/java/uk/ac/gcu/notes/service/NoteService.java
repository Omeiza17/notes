package uk.ac.gcu.notes.service;

import org.springframework.stereotype.Service;
import uk.ac.gcu.notes.entity.Note;
import uk.ac.gcu.notes.entity.Tag;
import uk.ac.gcu.notes.model.request.NoteRequest;
import uk.ac.gcu.notes.model.request.Request;
import uk.ac.gcu.notes.repository.NoteRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static java.util.Objects.isNull;

@Service
public class NoteService {
  private TagService tagService;
  private NoteRepository noteRepository;

  public NoteService(TagService tagService, NoteRepository noteRepository) {
    this.tagService = tagService;
    this.noteRepository = noteRepository;
  }

  /**
   * Checks if the datastore contains a {@link Note} object. If it does, update/return the existing object. If it
   * doesn't create a new {@link Note} instance, saves and return.
   *
   * @param request Request object for creating a {@link Note}.
   * @return Note
   */
  public Note create(NoteRequest request) {
    Note note = noteRepository.findByTitle(request.getTitle()).orElse(null);
    if (isNull(note)) {
      note = new Note();
    }
    note.setTitle(request.getTitle());
    note.setText(request.getText());
    List<Tag> tags = new ArrayList<>();
    request.getTags().forEach(request1 -> tags.add(tagService.create(new Request(request1))));
    note.setTags(tags);
    return noteRepository.save(note);
  }

  public List<Note> getNotes() {
    Iterable<Note> notes = noteRepository.findAll();
    return StreamSupport.stream(notes.spliterator(), false).collect(Collectors.toList());
  }
}
