package steps.online;

import cucumber.api.PendingException;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Given;
import steps.driver.WebDriverWrapper;
import steps.site.MassiveMailerSite;

public class QuestionListSteps {
    private final MassiveMailerSite site = new MassiveMailerSite();
    private final WebDriverWrapper driver = site.getDriver();


    @Given("^Questionに一件も登録されていない$")
    public void questionに一件も登録されていない() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        // throw new PendingException();

        // ０件なのであとでデータを消すとか処理が必要かも

    }

    @When("^左部のサイドバーのQuestionListをクリックしたとき$")
    public void 左部のサイドバーのquestionlistをクリックしたとき() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        // throw new PendingException();
        site.visit("onlinetest/question_list.jsp");
    }

    @Then("^QuestonList画面にヘッダのみ表示される$")
    public void questonlist画面にヘッダのみ表示される() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        // throw new PendingException();
        driver.pageShouldContain("QuestionID Description Edit");
    }
}
