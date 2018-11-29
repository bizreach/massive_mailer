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
import java.util.stream.Collectors;

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
    public void doPostEditQuestion() throws Exception {
        // Arrange
        final String redirectUrl = "/onlinetest/question_list.jsp";

        final String testDescription = "Choose Scrum's word.";
        final String testAdvice = "Read Scrum Guide";

        QuestionAndOption questionAndOption = addTestQuestion();
        Question question = questionAndOption.getQuestion();
        List<AnswerOption> optionList = questionAndOption.getOptionList();

        request.addParameter("questionId", String.valueOf(question.getId()));
        request.addParameter("description", testDescription);
        request.addParameter("advice", testAdvice);

        Map<String, String> testCases = new HashMap<>();
        for (int i = 0; i < optionList.size(); i++) {
            String key =  "option" + i;
            String value  = "hogehoge" + i;
            testCases.put(key, value);
            request.setParameter(key, value);
        }

        // Act
        controller.doPost(request, response);

        // Assert
        assertEquals(redirectUrl, response.getRedirectedUrl());

        Question editedQuestion = Question.getById((Long) question.getId());
        assertEquals(question.getId(), Long.valueOf((Integer) editedQuestion.getId()));
        assertEquals(editedQuestion.getDescription(), testDescription);
        assertEquals(editedQuestion.getAdvice(), testAdvice);

        List<AnswerOption> editedOptionList = new ArrayList<>(editedQuestion.getOptions());
        for (int i = 0; i < editedOptionList.size(); i++) {
            AnswerOption editedOption = editedOptionList.get(i);
            assertEquals(testCases.get("option" + i), editedOption.getDescription());
        }
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
