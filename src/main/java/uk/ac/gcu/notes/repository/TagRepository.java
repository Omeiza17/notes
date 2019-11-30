package uk.ac.gcu.notes.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import uk.ac.gcu.notes.entity.Tag;

import java.util.Optional;

@RepositoryRestResource
public interface TagRepository extends MongoRepository<Tag, Long> {
  Optional<Tag> findByName(String title);
}
