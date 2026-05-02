package org.themavenlab.quizapp;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

@JsonTest
public class QuizApplicationQuestionTest {

    @Autowired
    private JacksonTester<Question> json;

    @Test
    void questionSerializationTest() throws IOException {
        Question question = new Question(1L, "How filters are used in Spring Web?", "Spring Framework");
        assertThat(json.write(question)).isStrictlyEqualToJson("expectedQuestion.json");

        assertThat(json.write(question)).hasJsonPath("@.id");
        assertThat(json.write(question)).extractingJsonPathNumberValue("@.id").isEqualTo(1);
        assertThat(json.write(question)).hasJsonPathStringValue("@.text");
        assertThat(json.write(question)).extractingJsonPathStringValue("@.text").isEqualTo(
                "How filters are used in Spring Web?");
        assertThat(json.write(question)).hasJsonPathStringValue("@.category");
        assertThat(json.write(question)).extractingJsonPathStringValue("@.category").isEqualTo(
                "Spring Framework");
    }
}
