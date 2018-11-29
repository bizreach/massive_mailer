package com.odde.massivemailer.controller.onlinetest;

import com.odde.massivemailer.controller.AppController;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/onlinetest/edit_question")
public class EditQuestionController extends AppController {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("description", "2 +3 = ?");
        request.setAttribute("option1", "1");
        request.setAttribute("option2", "2");
        request.setAttribute("option3", "3");
        request.setAttribute("option4", "4");
        request.setAttribute("option5", "5");
        request.setAttribute("option6", "6");
        request.setAttribute("advice", "2 + 3 = 5です");
        RequestDispatcher dispatcher = request.getRequestDispatcher("/onlinetest/edit_question.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("/onlinetest/question_list.jsp");
    }

    protected class EditQuestionParams{
        public String description;
        public Long questionId;
        public List<Long> options;
        public String advice;
    }

    protected EditQuestionParams parseParams(HttpServletRequest request){
        return new EditQuestionParams();
    }
}
