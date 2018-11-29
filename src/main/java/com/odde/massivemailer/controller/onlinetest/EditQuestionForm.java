package com.odde.massivemailer.controller.onlinetest;

import java.util.List;

/**
 * @author yuto.eguma
 */
public class EditQuestionForm {
    public Long questionId;
    public String description;
    public List<Long> options;
    public String advice;

    public EditQuestionForm(long questionId, String description){
        this.questionId=questionId;
        this.description=description;
    }

}
