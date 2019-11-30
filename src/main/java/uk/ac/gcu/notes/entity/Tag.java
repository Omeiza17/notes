package uk.ac.gcu.notes.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "tags")
public class Tag extends AbstractDocument {
  private String name;
  public Tag(String name) {
    this.name = name;
  }
}
