package uk.ac.gcu.notes.model.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class NoteResponse extends Response {
  private String text;
  private List<Response> tags;
}
