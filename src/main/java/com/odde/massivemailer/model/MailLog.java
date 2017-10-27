package com.odde.massivemailer.model;

import org.javalite.activejdbc.Base;
import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.annotations.Table;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Table("mail_logs")
public class MailLog extends Model {



    public static List<Map> getReport() {
        return Base.findAll("SELECT c.email, m.sent_at, c.location, COUNT(m.course_id) AS course_count FROM mail_logs m INNER JOIN contact_people c ON m.contact_person_id = c.id GROUP BY m.contact_person_id, m.sent_at");
    }

    public  void create(Object contact_person_id, Date sent_at, Course course) {
        set("contact_person_id", contact_person_id);
        set("sent_at", sent_at);
        set("course_id", course.getId());
        saveIt();
    }

    public static boolean isExist(ContactPerson contactPerson, Course course) {
        return MailLog.findFirst("contact_person_id = ? AND course_id = ?", contactPerson.getId(), course.getId()) != null;
    }

    public static boolean isExist(ContactPerson contactPerson, List<Course> courses) {
        for (Course course: courses) {
            if (!MailLog.isExist(contactPerson, course)) {
                return false;
            }
        }
        return true;
    }
}
