package uk.ac.gcu.notes.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uk.ac.gcu.notes.entity.Tag;
import uk.ac.gcu.notes.model.request.Request;
import uk.ac.gcu.notes.service.TagService;

import java.util.List;

@RestController
@RequestMapping(path = "/tags")
public class TagController {
  private TagService tagService;

  public TagController(TagService tagService) {
    this.tagService = tagService;
  }

  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public Tag create(@RequestBody Request request) {
    return tagService.create(request);
  }

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public List<Tag> getTags() {
    return tagService.getTags();
  }
}
