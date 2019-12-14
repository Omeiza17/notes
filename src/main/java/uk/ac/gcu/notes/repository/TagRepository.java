package uk.ac.gcu.notes.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import uk.ac.gcu.notes.entity.Tag;

import java.util.Optional;

@Repository
public interface TagRepository extends MongoRepository<Tag, String> {
  Optional<Tag> findByName(String title);
}
