package org.themavenlab.quizapp;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/questions")
public class QuizApplicationController {

  @GetMapping("/{requestedId}")
  private ResponseEntity<Question> findById(@PathVariable Long requestedId) {
    if (requestedId != 12L) {
      return ResponseEntity.notFound().build();
    } else {
      Question question = new Question(12L, "How filters are used in Spring Web?", "Spring Framework");
      return ResponseEntity.ok(question);
    }
  }
}
