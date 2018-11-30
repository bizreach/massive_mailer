package com.odde.massivemailer.controller.onlinetest;

import com.odde.TestWithDB;
import com.odde.massivemailer.model.onlinetest.AnswerOption;
import com.odde.massivemailer.model.onlinetest.Question;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
        final QuestionAndOption questionAndOption = addTestQuestion();
        final Question question = questionAndOption.getQuestion();
        final List<AnswerOption> optionList = questionAndOption.getOptionList();
        final Long questionId = (Long) question.getId();
        request.setParameter("question_id", String.valueOf(questionId));

        // Act
        controller.doGet(request, response);

        // Assert
        assertEquals(expectedHttpStatus, response.getStatus());
        assertEquals(forwardedUrl, response.getForwardedUrl());
        assertEquals(question.getDescription(), request.getAttribute("description"));


        for (int i = 0; i < optionList.size(); i++) {
            final AnswerOption option = optionList.get(i);
            assertEquals(option.getDescription(), request.getAttribute("option" + i));
        }

        assertEquals(question.getAdvice(), request.getAttribute("advice"));
    }

    @Test
    public void test_doPostEditQuestion_redirect() throws Exception {
        // ### Arrange ###
        final String redirectUrl = "/onlinetest/question_list.jsp";
        createDefaultRequestWithTestData();

        // ### Act ###
        controller.doPost(request, response);

        // ### Assert ###
        assertEquals(redirectUrl, response.getRedirectedUrl());
    }

    @Test
    public void test_doPostEditQuestion_updateData() throws Exception {
        // ### Arrange ###
        final String testDescription = "Choose Scrum's word.";
        final String testAdvice = "Read Scrum Guide";
        final Map<String, String> optionTestCases = new HashMap<String, String>() {
            {
                put("option1", "Java");
                put("option2", "Python");
                put("option3", "Planning");
                put("option4", "Assembler");
                put("option5", "C");
                put("option6", "Lisp");
            }
        };
        final String testCheck = "3";

        // create test data
        final QuestionAndOption questionAndOption = addTestQuestion();
        final Question question = questionAndOption.getQuestion();

        // prepare request
        createTestRequest(testDescription, testAdvice, optionTestCases, testCheck, question);

        // ### Act ###
        controller.doPost(request, response);

        // ### Assert ###
        assertEditedQuestion(testDescription, testAdvice, optionTestCases, (Long) question.getId());
    }

    private void createDefaultRequestWithTestData() {
        // create test data
        final QuestionAndOption questionAndOption = addTestQuestion();
        final Question question = questionAndOption.getQuestion();

        // create default request
        createDefaultRequest(question);
    }

    private void assertEditedQuestion(String testDescription, String testAdvice, Map<String, String> optionTestCases, Long questionId) {
        Question editedQuestion = Question.getById(questionId);
        assertEquals(questionId, Long.valueOf((Integer) editedQuestion.getId()));
        assertEquals(editedQuestion.getDescription(), testDescription);
        assertEquals(editedQuestion.getAdvice(), testAdvice);

        List<AnswerOption> editedOptionList = new ArrayList<>(editedQuestion.getOptions());
        assertEquals(optionTestCases.get("option1"), editedOptionList.get(0).getDescription());
        assertEquals(optionTestCases.get("option2"), editedOptionList.get(1).getDescription());
        assertEquals(optionTestCases.get("option3"), editedOptionList.get(2).getDescription());
        assertEquals(optionTestCases.get("option4"), editedOptionList.get(3).getDescription());
        assertEquals(optionTestCases.get("option5"), editedOptionList.get(4).getDescription());
        assertEquals(optionTestCases.get("option6"), editedOptionList.get(5).getDescription());
        assertFalse(editedOptionList.get(0).isCorrect());
        assertFalse(editedOptionList.get(1).isCorrect());
        assertTrue(editedOptionList.get(2).isCorrect());
        assertFalse(editedOptionList.get(3).isCorrect());
        assertFalse(editedOptionList.get(4).isCorrect());
        assertFalse(editedOptionList.get(5).isCorrect());
    }

    private void createDefaultRequest(Question question) {
        final String testDescription = "Choose Scrum's word.";
        final String testAdvice = "Read Scrum Guide";
        final Map<String, String> optionTestCases = new HashMap<String, String>() {
            {
                put("option1", "Python");
                put("option2", "Ceremony");
                put("option3", "TDD");
                put("option4", "Working Agreement");
                put("option5", "Terraform");
                put("option6", "Vim");
            }
        };
        final String testCheck = "2";
        createTestRequest(testDescription, testAdvice, optionTestCases, testCheck, question);
    }

    private void createTestRequest(String testDescription, String testAdvice, Map<String, String> optionTestCases, String testCheck, Question question) {
        request.addParameter("questionId", String.valueOf(question.getId()));
        request.addParameter("description", testDescription);
        request.addParameter("advice", testAdvice);
        request.addParameter("check", testCheck);
        optionTestCases.forEach((k, v) -> {
            request.setParameter(k, v);
        });
    }

    private QuestionAndOption addTestQuestion() {
        Question question = new Question();
        question.set("description", "Choose Scrum's word.");
        question.set("advice", "Read Scrum Guide");
        question.set("category", "test");
        question.saveIt();
        return new QuestionAndOption(question, addTestOptionList(question.getId()));
    }

    private List<AnswerOption> addTestOptionList(Object questionId) {
        return new ArrayList<AnswerOption>() {
            {
                add(addTestOption(questionId, "Python", false));
                add(addTestOption(questionId, "Ceremony", true));
                add(addTestOption(questionId, "TDD", false));
                add(addTestOption(questionId, "Working Agreement", false));
                add(addTestOption(questionId, "Terraform", false));
                add(addTestOption(questionId, "Vim", false));
            }
        };
    }

    private AnswerOption addTestOption(Object questionId, String description, boolean isCorrect) {
        AnswerOption option = new AnswerOption();
        option.set("question_id", questionId);
        option.set("description", description);
        option.setIsCorrect(isCorrect);
        option.saveIt();
        return option;
    }

    /**
     * helper class for test
     */
    static private class QuestionAndOption {

        private Question question;
        private List<AnswerOption> optionList;

        public QuestionAndOption(Question question, List<AnswerOption> optionList) {
            this.question = question;
            this.optionList = optionList;
        }

        public Question getQuestion() {
            return question;
        }

        public List<AnswerOption> getOptionList() {
            return optionList;
        }
    }
}
