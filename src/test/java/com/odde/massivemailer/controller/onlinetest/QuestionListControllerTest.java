package com.odde.massivemailer.controller.onlinetest;

import com.odde.TestWithDB;
import com.odde.massivemailer.model.onlinetest.Question;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;


@RunWith(TestWithDB.class)
public class QuestionListControllerTest {
    private QuestionListController controller = new QuestionListController();
    private final MockHttpServletRequest request = new MockHttpServletRequest();
    private final MockHttpServletResponse response = new MockHttpServletResponse();

    @Test
    public void doGetQuestion() throws IOException {
        controller.doGet(request, response);
        List<Question> questions = (List<Question>) request.getAttribute("questions");
        Question question = questions.get(0);
        assertEquals(question.getId(), 1L);
        assertEquals(question.getDescription(), "Scrumの用語はどれか？");
    }
}
