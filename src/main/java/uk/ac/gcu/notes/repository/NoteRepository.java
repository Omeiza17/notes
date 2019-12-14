package uk.ac.gcu.notes.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import uk.ac.gcu.notes.entity.Note;

import java.util.Optional;

@Repository
public interface NoteRepository extends MongoRepository<Note, String> {
  Optional<Note> findByTitle(String title);
}
