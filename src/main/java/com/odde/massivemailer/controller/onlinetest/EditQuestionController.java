package com.odde.massivemailer.controller.onlinetest;

import com.odde.massivemailer.controller.AppController;
import com.odde.massivemailer.model.onlinetest.AnswerOption;
import com.odde.massivemailer.model.onlinetest.Question;
import org.javalite.activejdbc.Model;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@WebServlet("/onlinetest/edit_question")
public class EditQuestionController extends AppController {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // question のデータを取得
        String questionIdStr = request.getParameter("question_id");
        Question question = Question.getById(Long.valueOf(questionIdStr));
        ArrayList<AnswerOption> options = new ArrayList(question.getOptions());


        // 画面に渡すパラメータの set
        request.setAttribute("description", question.getDescription());
        for (int i = 0;i < options.size();i++) {
            request.setAttribute("option" + i,options.get(i).getDescription());
        }
        request.setAttribute("advice", question.getAdvice());
        RequestDispatcher dispatcher = request.getRequestDispatcher("/onlinetest/edit_question.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // UPDATE
        EditQuestionForm form = this.parseParams(request);
        saveQuestion(request, form);

        response.sendRedirect("/onlinetest/question_list.jsp");
    }

    private void saveQuestion(HttpServletRequest request, EditQuestionForm form) {
        Question question = new Question();
        question.setDescription(form.description);
        question.set("advice", form.advice);
        question.setId(form.questionId);
        question.saveIt();

        question.getOptions().forEach(Model::delete);

        Integer correctNumber = 1;
        for (int i = 0; i < 6 ; i++) {
            AnswerOption option = new AnswerOption();
            option.set("question_id", form.questionId);
            if (correctNumber == i + 1) {
                option.setIsCorrect(true);
            } else {
                option.setIsCorrect(false);
            }
            Optional.ofNullable(request.getParameter("option" + (i + 1))).ifPresent(description -> {
                option.set("description", description);
                option.saveIt();
            });
        }
    }

    protected EditQuestionForm parseParams(HttpServletRequest request) {
        return new EditQuestionForm(
                Long.valueOf(request.getParameter("question_id")),
                request.getParameter("description"),
                request.getParameter("advice")
        );
    }
}
