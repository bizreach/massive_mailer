package com.odde.massivemailer.controller.onlinetest;

import com.odde.TestWithDB;
import com.odde.massivemailer.model.onlinetest.Question;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(TestWithDB.class)
public class QuestionListControllerTest {

    private QuestionListController controller = new QuestionListController();
    private final MockHttpServletRequest request = new MockHttpServletRequest();
    private final MockHttpServletResponse response = new MockHttpServletResponse();

    @Before
    public void prepareTestData() {
        Question.createIt("description", "Scrumの用語はどれか？");
        Question.createIt("description", "Scrumの用語はどれか？");
        Question.createIt("description", "Scrumの用語はどれか？");
        Question.createIt("description", "Scrumの用語はどれか？");
        Question.createIt("description", "Scrumの用語はどれか？");
        Question.createIt("description", "Scrumの用語はどれか？");
        Question.createIt("description", "Scrumの用語はどれか？");
        Question.createIt("description", "Scrumの用語はどれか？");
        Question.createIt("description", "Scrumの用語はどれか？");
        Question.createIt("description", "Scrumの用語はどれか？");
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
    public void doGetQuestionAll() throws Exception {
        controller.doGet(request, response);
        List<Question> questions = (List<Question>) request.getAttribute("questions");
        long questionCount = questions.size();
        assertEquals((long) Question.count(), questionCount);
    }
}
