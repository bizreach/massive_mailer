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
        boolean result = driver.findElements(By.id("description")).stream()
            .anyMatch(el -> el.getAttribute("value").equals("2 +3 = ?"));
        assertTrue(result);
    }

    @Then("^Edit画面のoption(\\d+)に\"(.+)\"が表示される$")
    public void Edit画面のoption_に_が表示される(int i, String value) {
        boolean result = driver.findElements(By.id("option" + i + "_value")).stream()
            .anyMatch(el -> el.getAttribute("value").equals(value));
        assertTrue(result);
    }
    @Then("^Edit画面のAdviceに\"2 \\+ 3 = 5です\"が表示される$")
    public void Edit画面のAdviceに_が表示される() {
        boolean result = driver.findElements(By.id("advice")).stream()
            .anyMatch(el-> el.getAttribute("value").equals("2 + 3 = 5です"));
        assertTrue(result);
    }

    @Then("^Edit画面にsaveボタンがある$")
    public void Edit画面にsaveボタンがある() {
        boolean result = !driver.findElements(By.id("save")).isEmpty();
        assertTrue(result);
    }
}
