package com.odde.massivemailer.controller.onlinetest;

import com.odde.TestWithDB;
import com.odde.massivemailer.model.onlinetest.Question;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(TestWithDB.class)
public class QuestionListControllerTest {

    private QuestionListController controller = new QuestionListController();
    private final MockHttpServletRequest request = new MockHttpServletRequest();
    private final MockHttpServletResponse response = new MockHttpServletResponse();

    @Before
    public void prepareTestData() {
        for (int i = 0; i < 10; i++) {
            Question.createIt("description", "Scrumの用語はどれか？");
        }
    }

    @Test
    public void doGetRequest() throws ServletException, IOException {
        controller.doGet(request, response);
        String forwardedUrl = response.getForwardedUrl();
        assertEquals("/onlinetest/question_list.jsp", forwardedUrl);
    }

    @Test
    public void doGetQuestion() throws Exception {
        controller.doGet(request, response);
        List<Question> questions = (List<Question>) request.getAttribute("questions");
        Question anyQuestion = questions.stream().findFirst().orElseThrow(Exception::new);
        assertNotNull(anyQuestion.getId());
        assertNotNull(anyQuestion.getDescription());
    }

    @Test
    public void doGetQuestions() throws ServletException, IOException {
        controller.doGet(request, response);
        List<Question> questions = (List<Question>) request.getAttribute("questions");
        long questionCount = questions.size();
        assertEquals((long) Question.count(), questionCount);
    }

}
