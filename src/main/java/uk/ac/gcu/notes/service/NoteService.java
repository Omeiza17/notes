package uk.ac.gcu.notes.service;

import org.springframework.stereotype.Service;
import uk.ac.gcu.notes.entity.Note;
import uk.ac.gcu.notes.entity.Tag;
import uk.ac.gcu.notes.model.request.NoteRequest;
import uk.ac.gcu.notes.model.request.Request;
import uk.ac.gcu.notes.model.response.NoteResponse;
import uk.ac.gcu.notes.repository.NoteRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

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
  public NoteResponse create(NoteRequest request) {
    Note note = noteRepository.findByTitle(request.getTitle()).orElse(null);
    return createOrUpdateNote(request, note);
  }

  public List<Note> getNotes() {
    Iterable<Note> notes = noteRepository.findAll();
    return StreamSupport.stream(notes.spliterator(), false).collect(Collectors.toList());
  }

  public NoteResponse update(String noteId, NoteRequest request) {
    Note note = noteRepository.findById(noteId).orElse(null);
    return createOrUpdateNote(request, note);
  }

  private NoteResponse createOrUpdateNote(NoteRequest request, Note note) {
    if (isNull(note)) {
      note = new Note();
    }
    note.setTitle(request.getTitle());
    note.setText(request.getText());
    List<Tag> tags = new ArrayList<>();
    request.getTags().forEach(request1 -> tags.add(tagService.createTag(new Request(request1))));
    note.setTags(tags);
    Note save = noteRepository.save(note);
    return convert(save);
  }

  public void delete(String  noteId) {
    Note note = noteRepository.findById(noteId).orElse(null);
    if (nonNull(note)) {
      noteRepository.delete(note);
    }
  }

  public NoteResponse convert(Note note) {
    NoteResponse response = new NoteResponse();
    response.setId(note.getId());
    response.setTitle(note.getTitle());
    response.setText(note.getText());
    response.setTags(note.getTags().stream().map(tag -> tagService.convert(tag)).collect(Collectors.toList()));
    return response;
  }
}
