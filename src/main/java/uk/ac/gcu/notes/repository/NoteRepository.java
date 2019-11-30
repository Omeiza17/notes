package uk.ac.gcu.notes.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import uk.ac.gcu.notes.entity.Note;

import java.util.Optional;

@RepositoryRestResource
public interface NoteRepository extends MongoRepository<Note, Long> {
  Optional<Note> findByTitle(String title);
}
