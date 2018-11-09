package com.odde.massivemailer.model.onlinetest;

import com.odde.TestWithDB;
import org.javalite.activejdbc.Base;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;

@RunWith(TestWithDB.class)
public class OnlineTestTest {

    @Test
    public void shouldNotGetANewOnlineTestWithNQuestionIdsIfEnoughQuestionsInDatabase() {
        OnlineTest newOnlineTest = new OnlineTest(5);
        assertEquals(0, newOnlineTest.getNumberOfQuestions());
    }

    @Test
    public void shouldGetNextQuestionWhenHaveMoreQuestionsAvailable() {
        mockQuestion(5);
        OnlineTest newOnlineTest = new OnlineTest(5);
        assertEquals(5, newOnlineTest.getNumberOfQuestions());
    }

    @Test(expected = NoSuchElementException.class)
    public void shouldNotGetNextQuestionWhenNoMoreQuestionsLeft() {
        OnlineTest newOnlineTest = new OnlineTest(5);
        newOnlineTest.getNextQuestion();
    }

    @Test
    public void shouldNotRepeatQuestions() {
        mockQuestion(6);
        OnlineTest newOnlineTest = new OnlineTest(5);
        Set<Question> questions = new HashSet<>();
        while(newOnlineTest.hasNextQuestion()) {
            questions.add(newOnlineTest.getCurrentQuestion());
            newOnlineTest.moveToNextQuestion();
        }
        assertEquals(5, questions.size());
    }

    private static class Pattern {
        private String category;
        private int correctlyAnsweredCount;
        private String expected;

        private Pattern(String category, int correctlyAnsweredCount, String expected) {
            this.category = category;
            this.correctlyAnsweredCount = correctlyAnsweredCount;
            this.expected = expected;
        }
    }

    public static Pattern[] patterns = {
            new Pattern("Scrum", 1, "よくできました"),
            new Pattern("Scrum", 0, "Scrumをもっと勉強して"),
            new Pattern("TDD", 0, "TDDをもっと勉強して"),
    };

    @Test
    public void showCategoryMessage() {
        // we use for the loop, because...
        // - in JUnit4, tests must be static and it cannot use DB at parameterized test
        // - to use JUnit5, we have to migrate this file - @RunWith to @ExtendedWith
        for (Pattern pattern: patterns) {
            mockQuestion(1, pattern.category);
            OnlineTest onlineTest = new OnlineTest(1);
            onlineTest.setCorrectAnswerCount(pattern.correctlyAnsweredCount);
            assertEquals(pattern.expected, onlineTest.getCategoryMessage());
            Base.rollbackTransaction();
        }
    }

    private static void mockQuestion(int numberOfQuestion, String category) {
        IntStream.range(0, numberOfQuestion).forEach(index -> Question.createIt("description", "desc" + index, "advice", "adv" + index, "category", category));
    }

    private static void mockQuestion(int numberOfQuestion) {
        IntStream.range(0, numberOfQuestion).forEach(index -> Question.createIt("description", "desc" + index, "advice", "adv" + index));
    }
}
