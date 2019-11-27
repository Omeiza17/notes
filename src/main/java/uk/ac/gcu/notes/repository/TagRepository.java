package uk.ac.gcu.notes.repository;

import org.springframework.cloud.gcp.data.datastore.repository.DatastoreRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import uk.ac.gcu.notes.entity.Tag;

import java.util.Optional;

@RepositoryRestResource
public interface TagRepository extends DatastoreRepository<Tag, Long> {
  Optional<Tag> findByName(String title);
}
