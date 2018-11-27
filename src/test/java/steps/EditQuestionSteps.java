package steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import steps.driver.WebDriverWrapper;
import steps.site.MassiveMailerSite;
import org.openqa.selenium.By;

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
        boolean result = driver.findElements(By.id("description")).stream().anyMatch(el->
           el.getAttribute("value").equals("2 +3 = ?")
        );
        assertTrue(result);
    }
}
