package uk.ac.gcu.notes.repository;

import org.springframework.cloud.gcp.data.datastore.repository.DatastoreRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import uk.ac.gcu.notes.entity.Note;

import java.util.Optional;

@RepositoryRestResource
public interface NoteRepository extends DatastoreRepository<Note, Long> {
  Optional<Note> findByTitle(String title);
}
