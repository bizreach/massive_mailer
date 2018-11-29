package com.odde.massivemailer.controller.onlinetest;

import com.odde.TestWithDB;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

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

        final int questionId = 2;
        final String description = "2 +3 = ?";
        final String option1 = "1";
        final String option2 = "2";
        request.setParameter("questionId", String.valueOf(questionId));

        // Act
        controller.doGet(request, response);

        // Assert
        assertEquals(expectedHttpStatus, response.getStatus());
        assertEquals(forwardedUrl, response.getForwardedUrl());
        assertEquals(description, request.getAttribute("description"));
        assertEquals(option1, request.getAttribute("option1"));
        assertEquals(option2, request.getAttribute("option2"));
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
}
