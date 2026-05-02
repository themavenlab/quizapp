package org.themavenlab.quizapp;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

@JsonTest
public class QuizApplicationOptionTest {

	@Autowired
	private JacksonTester<Option> json;

	@Test
	void optionSerializationTest() throws IOException {
		Option option = new Option(1L,
				"Filters are called before a request hits the DispatcherServlet. They allow for interception-style, chained processing of web requests for security, timeouts, and other purposes.",
				true);
		assertThat(json.write(option)).isStrictlyEqualToJson("expectedOption.json");

		assertThat(json.write(option)).hasJsonPath("@.id");
		assertThat(json.write(option)).extractingJsonPathNumberValue("@.id").isEqualTo(1);
		assertThat(json.write(option)).hasJsonPathStringValue("@.text");
		assertThat(json.write(option)).extractingJsonPathStringValue("@.text").isEqualTo(
				"Filters are called before a request hits the DispatcherServlet. They allow for interception-style, chained processing of web requests for security, timeouts, and other purposes.");
		assertThat(json.write(option)).hasJsonPathBooleanValue("@.isCorrect");
		assertThat(json.write(option)).extractingJsonPathBooleanValue("@.isCorrect").isEqualTo(true);
	}

	@Test
	void optionDeserializationTest() throws IOException {
		String expected = """
				{
				  "id": 1,
				  "text": "Filters are called before a request hits the DispatcherServlet. They allow for interception-style, chained processing of web requests for security, timeouts, and other purposes.",
					"isCorrect": true
				}
				""";

		assertThat(json.parse(expected)).isEqualTo(new Option(1L,
				"Filters are called before a request hits the DispatcherServlet. They allow for interception-style, chained processing of web requests for security, timeouts, and other purposes.",
				true));

		assertThat(json.parseObject(expected).id()).isEqualTo(1L);
		assertThat(json.parseObject(expected).text()).isEqualTo(
				"Filters are called before a request hits the DispatcherServlet. They allow for interception-style, chained processing of web requests for security, timeouts, and other purposes.");
		assertThat(json.parseObject(expected).isCorrect()).isEqualTo(true);
	}

}
