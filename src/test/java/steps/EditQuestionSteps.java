package steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.openqa.selenium.WebElement;
import steps.driver.WebDriverWrapper;
import steps.site.MassiveMailerSite;
import org.openqa.selenium.By;

import static org.junit.Assert.*;

public class EditQuestionSteps {
    private final MassiveMailerSite site = new MassiveMailerSite();
    private final WebDriverWrapper driver = site.getDriver();

    @Given("^QuestionのIDは2である$")
    public void EditQuestion() {
        site.visit("onlinetest/edit_question.jsp");
        assertEquals(driver.getCurrentTitle(), "Edit Question");
    }

    @Then("^Edit画面に更新用formが表示される")
    public void Edit画面に更新用formが表示される() {
        WebElement description = findElementById("editQuestion");
        assertEquals("editQuestion", description.getAttribute("name"));
        assertEquals("edit_question", description.getAttribute("action"));
        assertEquals("post", description.getAttribute("method"));
    }

    @Then("^Descriptionに\"2 \\+3 = \\?\"が表示される$")
    public void descriptionに_が入力されている() {
        WebElement description = findElementById("description");
        assertEquals(description.getAttribute("value"), "2 +3 = ?");
    }

    @Then("^Edit画面のoption(\\d+)に\"(.+)\"が表示される$")
    public void Edit画面のoption_に_が表示される(int i, String value) {
        WebElement option = findElementById("option" + i + "_value");
        assertEquals(option.getAttribute("value"), value);
    }
    @Then("^Edit画面のAdviceに\"2 \\+ 3 = 5です\"が表示される$")
    public void Edit画面のAdviceに_が表示される() {
        WebElement advice = findElementById("advice");
        assertEquals(advice.getAttribute("value"), "2 + 3 = 5です");
    }

    @Then("^Edit画面に(.+)ボタンがある$")
    public void Edit画面にボタンがある(String buttonName) {
        WebElement saveButton = findElementById(buttonName + "-button");

        assertEquals(saveButton.getAttribute("name"), buttonName + "-button");
        assertEquals(saveButton.getAttribute("value"), buttonName);
        assertEquals(saveButton.getText().toLowerCase(), buttonName);
    }

    @Then("^Edit画面の(.+)ボタンを押す$")
    public void Edit画面の_ボタンを押す(String buttonName) {
        driver.clickById(buttonName + "-button");
    }

    @Then("^Edit画面に更新されましたというメッセージが表示される$")
    public void Edit画面に_というメッセージが表示される() {
        WebElement receivedMessage = findElementById("receivedMessage");
        assertEquals("更新されました", receivedMessage.getText());
    }

    @Then("^QuestionList画面に遷移する$")
    public void QuestionList画面に遷移する() {
        WebElement questionListHeader = driver.findElements(By.className("page-header")).get(0);
        assertEquals("Question List", questionListHeader.getText());
    }

    private WebElement findElementById(String id) {
        return driver.findElements(By.id(id)).stream()
                .findFirst()
                .orElseThrow(() -> new AssertionError("#" + id + "に対応するコンポーネントがありませんでした"));
    }
}
