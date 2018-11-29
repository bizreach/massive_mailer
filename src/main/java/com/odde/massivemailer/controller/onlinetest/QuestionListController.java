package com.odde.massivemailer.controller.onlinetest;

import com.odde.massivemailer.controller.AppController;
import com.odde.massivemailer.model.onlinetest.Question;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/onlinetest/question_list")
public class QuestionListController extends AppController {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Question> questions = Question.findAll();
        request.setAttribute("questions", questions);
        RequestDispatcher dispatch = request.getRequestDispatcher("/onlinetest/question_list.jsp");
        dispatch.forward(request, response);
    }
}
