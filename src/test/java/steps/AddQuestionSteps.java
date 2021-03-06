package steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import steps.driver.WebDriverWrapper;
import steps.site.MassiveMailerSite;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class AddQuestionSteps {
    private final MassiveMailerSite site = new MassiveMailerSite();
    private final WebDriverWrapper driver = site.getDriver();

    @Given("^Add Questionを開いている$")
    public void AddQuestion() {
        site.visit("onlinetest/add_question.jsp");
        assertEquals(driver.getCurrentTitle(), "Add Question");
    }

    @Then("^\"([^\"]*)\" というメッセージが表示されていない$")
    public void というメッセージが表示されていない(String errorMessage) throws Throwable {
        assertFalse(driver.getBodyText().contains(errorMessage));
    }

    @Given("^Descriptionに\"([^\"]*)\" を入力する$")
    public void descriptionに_が入力されている(String description) throws Throwable {
        driver.setTextField("description", description);
    }

    @Given("^option(\\d+)に\"([^\"]*)\"を入力する$")

    public void option_に_を入力する(int optionNumber, String optionName) throws Throwable {
        driver.setTextField("option" + optionNumber, optionName);
    }


    @Given("^\"([^\"]*)\"番目のoptionを選択する$")
    public void 番目のoptionを選択する(String selectedNumber) throws Throwable {
        if (!"".equals(selectedNumber)) {
            driver.clickById("option" + selectedNumber);
        }
    }

    @Given("^adviceに \"([^\"]*)\" を入力する$")
    public void adviceに_を入力する(String advice) throws Throwable {
        driver.setTextField("advice", advice);
    }

    @When("^Addボタンを押す$")
    public void addボタンを押す() throws Throwable {
        driver.clickButton("add_button");
    }

    class Result {
        boolean found;
    }

    @Then("^\"([^\"]*)\"というメッセージが表示される$")
    public void というメッセージが表示される(String errorMessage) throws Throwable {
        Result r = new Result();
        driver.findElements(By.className("alert")).forEach(el -> {
            if (el.getText().contains(errorMessage)) {
                r.found = true;
            }
        });
        Assert.assertTrue(r.found);
    }

    @Given("^Option(\\d+) に\"([^\"]*)\"文字を入力する$")
    public void option_に_文字を入力する(int optionNumber, int optionCount) throws Throwable {
        String temporaryText = StringUtils.repeat("a", optionCount);
        driver.setTextField("option" + optionNumber, temporaryText);
    }

    @Given("^Description に \"([^\"]*)\" 文字を入力する$")
    public void description_に_文字を入力する(int descriptionLength) throws Throwable {
        String temporaryText = StringUtils.repeat("a", descriptionLength);
        driver.setTextField("description", temporaryText);
    }


    @Given("^\"([^\"]*)\"を回答として選択済み$")
    public void を回答として選択済み(String optionId) throws Throwable {
        driver.clickById(optionId);
    }

    @Given("^adviceが に\"([^\"]*)\"文字を入力する$")
    public void adviceが_に_文字を入力する(int adviceTextCount) throws Throwable {
        String temporaryText = StringUtils.repeat("a", adviceTextCount);
        driver.setTextField("advice", temporaryText);
    }

    @Then("^質問の一覧に遷移する$")
    public void 質問の一覧に遷移する() throws Throwable {
        assertEquals(driver.getCurrentTitle(), "Question List");
    }

    @Given("^advice に\"([^\"]*)\" を入力する$")
    public void advice_に_を入力する(String advice) throws Throwable {
        driver.setTextField("advice", advice);
    }

    @When("^OnlineTestを開始する$")
    public void onlinetestを開始する() throws Throwable {
        site.visit("onlinetest/launchQuestion");
    }

    @Then("^\"([^\"]*)\"という問題が出題される$")
    public void という問題が出題される(String description) throws Throwable {
        assertEquals(driver.findElementById("description").getText(),description);
    }

    @Then("^option(\\d+)に\"([^\"]*)\"が表示される$")
    public void option_に_が表示される(int optionNumber, String optionName) throws Throwable {
        assertTrue(driver.getBodyText().contains(optionName));
    }

    @Then("^\"([^\"]*)\"を回答として選択する$")
    public void を回答として選択する(String optionName) throws Throwable {
        driver.clickById(optionName);
    }

    @Then("^Answerボタンを押す$")
    public void answerボタンを押す() throws Throwable {
        driver.clickById("answer");
    }

    @Then("^EndOfTheTestが表示される$")
    public void endofthetestが表示される() throws Throwable {
        assertEquals(driver.getCurrentTitle(), "End Of Test");
    }
}
