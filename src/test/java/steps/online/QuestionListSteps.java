package steps.online;

import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import steps.driver.WebDriverWrapper;
import steps.site.MassiveMailerSite;

import java.util.Map;

public class QuestionListSteps {
    private final MassiveMailerSite site = new MassiveMailerSite();
    private final WebDriverWrapper driver = site.getDriver();
    private DataTable questionDataTable;


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

    @Given("^Questionが(\\d+)件登録されている、(\\d+)件目に「Scrumの用語はどれか？」というDescriptionで登録する$")
    public void questionが_件登録されている_件目に_Scrumの用語はどれか_というDescriptionで登録する(int arg1, int arg2, DataTable arg3) throws Throwable {
        questionDataTable = arg3;
    }

    @Then("^(\\d+)件目のQuestionIDが\"([^\"]*)\"と表示しされている事$")
    public void 件目のquestionidがと表示しされている事(int arg0, String arg1) {
        //System.out.println(questionDataTable);
        String description = questionDataTable.asMap(String.class,String.class).get(String.valueOf(arg0));
        Map.Entry<String, String> questionIdDescriptionEntry = questionDataTable.asMap(String.class, String.class).entrySet().stream().findFirst().orElseThrow(PendingException::new);
        driver.pageShouldContain(questionIdDescriptionEntry.getKey()+ " " + questionIdDescriptionEntry.getValue() + " Edit");
    }

    @And("^QuestionListが(\\d+)件表示され、Questionが(\\d+)行ずつ図のように表示される$")
    public void questionlistが件表示されQuestionが行ずつ図のように表示される(int arg0, int arg1) {
        //System.out.println(questionDataTable);
        String description = questionDataTable.asMap(String.class,String.class).get(String.valueOf(arg0));
        //System.out.println(description);
        //Map.Entry<String, String> questionIdDescriptionEntry = questionDataTable.asMap(String.class, String.class).entrySet().stream().findFirst().orElseThrow(PendingException::new);
        driver.pageShouldContain(String.valueOf(arg0) + " " + description + " Edit");
    }

    @And("^「QuestionID」「Description」「Edit」カラムが表示されている事$")
    public void questionidDescriptionEditカラムが表示されている事() {
    }

    @And("^(\\d+)件目のDescriptionに\"([^\"]*)\"と表示されるされている事$")
    public void 件目のdescriptionにと表示されるされている事(int arg0, String arg1) {
    }

    @And("^「Edit」というキャプションのボタンが(\\d+)件毎に表示される事$")
    public void editというキャプションのボタンが件毎に表示される事(int arg0) {
    }

    @And("^QuestionListの一番上にはQuestionIDの昇順で表示される事$")
    public void questionlistの一番上にはquestionidの昇順で表示される事() {
    }

}
