package steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
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

    @Then("^Descriptionに\"2 \\+3 = \\?\"が表示される$")
    public void descriptionに_が入力されている() {
        assertTrue(driver.getBodyText().contains("2 +3 = ?"));
    }
}
