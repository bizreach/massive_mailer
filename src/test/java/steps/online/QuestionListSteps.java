package steps.online;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Given;
import steps.driver.WebDriverWrapper;
import steps.site.MassiveMailerSite;

public class QuestionListSteps {
    private final MassiveMailerSite site = new MassiveMailerSite();
    private final WebDriverWrapper driver = site.getDriver();


    @Given("^Questionに一件も登録されていない$")
    public void Questionに一件も登録されていない() {

        // ０件なのであとでデータを消すとか処理が必要かも

    }

    @When("^左部のサイドバーのQuestionListをクリックしたとき$")
    public void 左部のサイドバーのQuestionListをクリックしたとき() {
        site.visit("onlinetest/question_list.jsp");
    }

    @Then("^QuestonList画面にヘッダのみ表示される$")
    public void QuestonList画面にヘッダのみ表示される() {
        driver.pageShouldContain("QuestionID Description Edit");
    }
}
