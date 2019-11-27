package uk.ac.gcu.notes.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.cloud.gcp.data.datastore.core.mapping.Entity;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "tags")
public class Tag extends AbstractDocument {
  private String name;

  public Tag(String name) {
    this.name = name;
  }
}
