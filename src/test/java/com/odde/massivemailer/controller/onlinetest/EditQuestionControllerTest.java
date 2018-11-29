package com.odde.massivemailer.controller.onlinetest;

import com.odde.TestWithDB;
import com.odde.massivemailer.model.onlinetest.Question;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

@RunWith(TestWithDB.class)
public class EditQuestionControllerTest {

    private EditQuestionController controller;
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;

    @Before
    public void setUpMockService() {
        this.controller = new EditQuestionController();
        this.request = new MockHttpServletRequest();
        this.response = new MockHttpServletResponse();
    }

    @Test
    public void doGetEditQuestion() throws Exception {
        // Arrange
        final int expectedHttpStatus = 200;
        final String forwardedUrl = "/onlinetest/edit_question.jsp";

        // 編集ページの仕様に関するもの
        Question question = addTestQuestion();
        Long questionId = (Long) question.getId();

        final Map<String, String> testCases = new HashMap<String, String>() {
            {
                put("option1", "1");
                put("option2", "2");
                put("option3", "3");
                put("option4", "4");
                put("option5", "5");
                put("option6", "6");
            }
        };
        request.setParameter("question_id", String.valueOf(questionId));

        // Act
        controller.doGet(request, response);

        // Assert
        assertEquals(expectedHttpStatus, response.getStatus());
        assertEquals(forwardedUrl, response.getForwardedUrl());
        assertEquals(question.getDescription(), request.getAttribute("description"));

        testCases.forEach((k, v) -> {
            assertEquals(v, request.getAttribute(k));
        });

        assertEquals(question.getAdvice(), request.getAttribute("advice"));
    }

    @Test
    public void doPostEditQuestion() throws Exception{
        // Arrange
        final String redirectUrl = "/onlinetest/question_list.jsp";

        // Act
        controller.doPost(request, response);

        // Assert
        assertEquals(redirectUrl, response.getRedirectedUrl());
    }

    private Question addTestQuestion() {
        Question question = new Question();
        question.set("description", "Choose Scrum's word.");
        question.set("advice", "Read Scrum Guide");
        question.set("category", "test");
        question.saveIt();
        return question;
    }
}
