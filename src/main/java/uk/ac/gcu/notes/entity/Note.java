package uk.ac.gcu.notes.entity;

import com.google.cloud.Timestamp;
import com.google.cloud.datastore.TimestampValue;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.cloud.gcp.data.datastore.core.mapping.Entity;
import org.springframework.data.annotation.Reference;
import org.springframework.util.Assert;
import uk.ac.gcu.notes.util.Translator;

import java.util.List;

@Getter
@NoArgsConstructor
@Entity(name = "notes")
public class Note extends AbstractDocument {
  @Setter
  private String title;
  @Setter
  private String text;
  private Timestamp timestamp = Timestamp.now();
  @Setter
  @Reference
  private List<Tag> tags;

  /**
   * Non-empty {@link Note} object constructor. Expects the title and text of the {@link Note} object.
   *
   * @param title Header of the {@link Note}.
   * @param text  Body of the {@link Note}.
   */
  public Note(String title, String text) {
    Assert.hasText(title, Translator.toLocale("non-empty.string", "'Note-title'"));
    Assert.hasText(text, Translator.toLocale("non-empty.string", "'Note-text'"));
    this.title = title;
    this.text = text;
  }

  public void setTimestamp(TimestampValue timestampValue) {
    this.timestamp = timestampValue.get();
  }
}
