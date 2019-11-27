package uk.ac.gcu.notes.service;

import org.springframework.stereotype.Service;
import uk.ac.gcu.notes.entity.Tag;
import uk.ac.gcu.notes.model.request.Request;
import uk.ac.gcu.notes.repository.TagRepository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static java.util.Objects.isNull;

@Service
public class TagService {
  private TagRepository tagRepository;

  public TagService(TagRepository tagRepository) {
    this.tagRepository = tagRepository;
  }

  /**
   * Checks if the datastore contains a {@link Tag} object. If it does, returns the existing object. If it doesn't
   * create a new {@link Tag} instance, saves and return.
   *
   * @param request {@link Object} containing a string for creating a tag.
   * @return Tag
   */
  public Tag create(Request request) {
    Tag tag = tagRepository.findByName(request.getTitle())
        .orElse(null);
    if (isNull(tag)) {
      tag = tagRepository.save(new Tag(request.getTitle()));
    }
    return tag;
  }

  public List<Tag> getTags() {
    Iterable<Tag> tags = tagRepository.findAll();
    return StreamSupport.stream(tags.spliterator(), false).collect(Collectors.toList());
  }

}
