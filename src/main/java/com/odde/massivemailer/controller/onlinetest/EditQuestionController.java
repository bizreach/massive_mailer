package com.odde.massivemailer.controller.onlinetest;

import com.odde.massivemailer.controller.AppController;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/onlinetest/edit_question")
public class EditQuestionController extends AppController {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // request.setAttribute("sample", "value");
        RequestDispatcher dispatcher = request.getRequestDispatcher("/onlinetest/edit_question.jsp");
        dispatcher.forward(request, response);
    }
}
