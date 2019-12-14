package uk.ac.gcu.notes.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uk.ac.gcu.notes.model.request.Request;
import uk.ac.gcu.notes.service.TagService;

@RestController
@RequestMapping(path = "/tags")
public class TagController {
  private TagService tagService;

  public TagController(TagService tagService) {
    this.tagService = tagService;
  }

  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> create(@RequestBody Request request) {
    return new ResponseEntity<>(tagService.create(request), HttpStatus.CREATED);
  }

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> getTags() {
    return new ResponseEntity<>(tagService.getTags(), HttpStatus.OK);
  }

  @DeleteMapping(path = "/{tagId}")
  public ResponseEntity<?> delete(@PathVariable String tagId) {
    tagService.delete(tagId);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
