package steps;

import com.odde.massivemailer.model.Course;
import com.odde.massivemailer.model.Participant;
import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import com.odde.massivemailer.model.ContactPerson;
import org.junit.Assert;
import steps.driver.WebDriverFactory;
import steps.driver.WebDriverWrapper;
import steps.page.CourseListPage;
import steps.page.EnrollParticipantPage;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SendPreviewMailTest {
    public static final String A_COURSE = "A course";
    private static final String BASE_URL = "http://localhost:8070/massive_mailer/";
    private WebDriverWrapper driver = WebDriverFactory.getDefaultDriver();
    TrackEmailSteps emailSteps = new TrackEmailSteps();

   /* private WebDriverWrapper driver = WebDriverFactory.getDefaultDriver();
    private String adminEmail, studentEmail;*/
    private void addCoursesAtTokyo(int offsetDate) throws Throwable {
        LocalDateTime d = LocalDateTime.now();
        DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        d.plusMonths(offsetDate);

        driver.visit(BASE_URL + "add_event.jsp");
        driver.setTextField("coursename", A_COURSE);
        driver.setTextField("duration", "30");
        driver.setDropdownValue("location", "Tokyo");
        driver.setTextField("startdate", d.format(f));
        driver.setTextField("address", "odd-e");
        driver.setTextField("coursedetails", "csd");
        driver.setTextField("instructor", "terry");

        driver.clickButton("save_button");
    }

    public void addContact(String email,String location) throws Throwable {
        driver.visit(BASE_URL  + "add_contact.jsp");
        driver.setTextField("email", email);
        driver.setDropdownValue("location", location);
        driver.clickButton("add_button");
    }

    @Given("^there is a course starting from \"([^\"]*)\"$")
    public void there_is_a_course_starting_from(String date) throws Throwable {
        EventTests eventTests = new EventTests();
        eventTests.addCourse(A_COURSE, "Singapore", date);
    }

    @Given("^there are students with email \"([^\"]*)\" loaded for this course$")
    public void there_are_students_with_email_loaded_for_this_course(String email) throws Throwable {
        new EnrollParticipantPage().addStudentTo(A_COURSE, email);
    }

    @When("^i send preview email for this course$")
    public void i_send_preview_email_for_this_course() throws Throwable {
        new CourseListPage().sendPreviewEmailFor(A_COURSE);
    }

    @Then("^\"([^\"]*)\" should receive the pre-course email$")
    public void should_receive_the_pre_course_email(String arg1) throws Throwable {
    }

    @When("^i send precourse email for this course$")
    public void i_send_precourse_email_for_this_course() throws Throwable {
        new CourseListPage().sendPrecourseEmailFor(A_COURSE);
    }

    @Given("^There is a contact \"([^\"]*)\" at Tokyo$")
    public void there_is_a_contact_at_Tokyo(String arg1) throws Throwable {
        addContact(arg1, "Tokyo");
    }

    @Given("^there are two courses at Tokyo$")
    public void there_are_two_courses_at_Tokyo() throws Throwable {
        addCoursesAtTokyo(1);
        addCoursesAtTokyo(30);
    }

    @When("^I trigger the sending twice$")
    public void i_trigger_the_sending_twice() throws Throwable {
        i_trigger_the_sending_once();
        driver.setWait(5);
        i_trigger_the_sending_once();
        driver.setWait(5);
    }

    @Given("^add contact \"([^\"]*)\" at Tokyo$")
    public void add_contact_at_Tokyo(String arg1) throws Throwable {
        there_is_a_contact_at_Tokyo(arg1);
    }

    @When("^I trigger the sending once$")
    public void i_trigger_the_sending_once() throws Throwable {
        driver.visit(BASE_URL + "coursedlist.jsp");
        driver.clickButton("send_button");
    }

    @Given("^there is a course at Tokyo$")
    public void there_is_a_course_at_Tokyo() throws Throwable {
        addCoursesAtTokyo(1);
    }

    @When("^Report page Includes$")
    public void report_page_Includes(DataTable arg1) throws Throwable {
        // TODO: implement
    }

    @Then("^\"([^\"]*)\" should receive email$")
    public void should_receive_email(String arg1) throws Throwable {
        // TODO: implement
    }

    @Then("^\"([^\"]*)\" shouldn't receive email$")
    public void shouldn_t_receive_email(String arg1) throws Throwable {
        driver.expectElementWithIdToContainText("message", "0 emails contain 0 events sent.");
    }
}
