package steps;

import cucumber.api.java.en.Given;
import steps.driver.WebDriverWrapper;
import steps.site.MassiveMailerSite;

import static org.junit.Assert.*;

public class EditQuestionSteps {
    private final MassiveMailerSite site = new MassiveMailerSite();
    private final WebDriverWrapper driver = site.getDriver();

    @Given("^ID2のEdit Questionを開いている$")
    public void EditQuestion() {
        site.visit("onlinetest/edit_question.jsp");
        assertEquals(driver.getCurrentTitle(), "Edit Question");
    }
}
