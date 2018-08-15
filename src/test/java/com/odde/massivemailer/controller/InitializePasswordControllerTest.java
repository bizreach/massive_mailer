package com.odde.massivemailer.controller;

import com.odde.TestWithDB;
import com.odde.massivemailer.model.ContactPerson;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import static org.junit.Assert.assertEquals;

@RunWith(TestWithDB.class)
public class InitializePasswordControllerTest {
    private InitializePasswordController controller;
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;
    @Before
    public void setUpMockService() {
        controller = new InitializePasswordController();

        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
    }

    @Test
    public void initialPassword() throws Exception {

        ContactPerson user = new ContactPerson("Test", "test@example.com", "Last");
        user.saveIt();
//        InitialPasswordToken token = InitialPasswordToken("token", user.getLongId());
//        token.saveIt();
        String password = "abcd1234";

        request.setParameter("token", "token");
        request.setParameter("password", password);
        request.setParameter("password_confirm", password);
        controller.doPost(request, response);

        assertEquals("abcd1234", user.getPassword());
        assertEquals("initialize-success.jsp", response.getRedirectedUrl());
    }
}