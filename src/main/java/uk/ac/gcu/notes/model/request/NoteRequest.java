package uk.ac.gcu.notes.model.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class NoteRequest extends Request {
  private String text;
  private List<String> tags;
}
