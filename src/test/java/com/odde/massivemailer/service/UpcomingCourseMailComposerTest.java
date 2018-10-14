package com.odde.massivemailer.service;

import com.odde.TestWithDB;
import com.odde.massivemailer.model.ContactPerson;
import com.odde.massivemailer.model.Course;
import com.odde.massivemailer.model.Mail;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(TestWithDB.class)
public class UpcomingCourseMailComposerTest {
    @Test
    public void WithContactInfo() {
        UpcomingCourseMailComposer composer = new UpcomingCourseMailComposer();
        ContactPerson contact = new ContactPerson();
        List<Course> courses = new ArrayList<>();
        Mail mail = composer.createUpcomingCourseMail(contact, courses);
        assertEquals(contact.getEmail(), mail.getReceipts().get(0));
    }

}