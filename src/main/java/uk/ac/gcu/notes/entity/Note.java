package uk.ac.gcu.notes.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.util.Assert;
import uk.ac.gcu.notes.util.Translator;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "notes")
public class Note extends AbstractDocument {
  private String title;
  private String text;
  @DBRef
  private List<Tag> tags;
  @LastModifiedDate
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private Date createdDate = new Date();

  /**
   * Non-empty {@link Note} object constructor. Expects the title and text of the {@link Note} object.
   *
   * @param title Header of the {@link Note}.
   * @param text  Body of the {@link Note}.
   */
  @SuppressWarnings("CheckStyle")
  public Note(String title, String text) {
    Assert.hasText(title, Translator.toLocale("non-empty.string", "'Note-title'"));
    Assert.hasText(text, Translator.toLocale("non-empty.string", "'Note-text'"));
    this.title = title;
    this.text = text;
  }

}
