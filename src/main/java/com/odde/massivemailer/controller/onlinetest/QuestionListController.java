package com.odde.massivemailer.controller.onlinetest;

import com.odde.massivemailer.controller.AppController;
import com.odde.massivemailer.model.onlinetest.Question;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class QuestionListController extends AppController {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
        List<Question> questions = Question.findAll();
        request.setAttribute("questions", questions);
    }
}
