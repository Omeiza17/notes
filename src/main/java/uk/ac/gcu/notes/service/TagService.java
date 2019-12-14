package uk.ac.gcu.notes.service;

import org.springframework.stereotype.Service;
import uk.ac.gcu.notes.entity.Tag;
import uk.ac.gcu.notes.model.request.Request;
import uk.ac.gcu.notes.model.response.Response;
import uk.ac.gcu.notes.repository.TagRepository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

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
  public Response create(Request request) {
    return convert(createTag(request));
  }

  public Tag createTag(Request request) {
    Tag tag = tagRepository.findByName(request.getTitle())
        .orElse(null);
    if (isNull(tag)) {
      tag = tagRepository.save(new Tag(request.getTitle()));
    }
    return tag;
  }

  public List<Response> getTags() {
    Iterable<Tag> tags = tagRepository.findAll();
    List<Tag> collect = StreamSupport.stream(tags.spliterator(), false).collect(Collectors.toList());
    return collect.stream().map(this::convert).collect(Collectors.toList());
  }

  public Response convert(Tag tag) {
    Response response = new Response();
    response.setId(tag.getId());
    response.setTitle(tag.getName());
    return response;
  }

  public void delete(String tagId) {
    Tag tag = tagRepository.findById(tagId).orElse(null);
    if (nonNull(tag)) {
      tagRepository.delete(tag);
    }
  }
}
