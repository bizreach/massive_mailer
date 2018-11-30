package steps.online;

import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import steps.driver.WebDriverWrapper;
import steps.site.MassiveMailerSite;

import java.util.List;

import static org.junit.Assert.assertEquals;

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

    @Then("^(\\d+)件目のQuestionのQuestionIDが\"([^\"]*)\"、Descriptionに\"([^\"]*)\"と表示されている事$")
    public void 件目のquestionのquestionidがDescriptionにと表示されている事(int arg0, String arg1, String arg2) throws Throwable {
        String description = questionDataTable.asMap(String.class,String.class).get(String.valueOf(arg0));
        driver.pageShouldContain(String.valueOf(arg0) + " " + description + " Edit");
    }

    @And("^QuestionListが(\\d+)件表示され、Questionが(\\d+)行ずつ図のように表示される$")
    public void questionlistが件表示されQuestionが行ずつ図のように表示される(int arg0, int arg1) {
        String description = questionDataTable.asMap(String.class,String.class).get(String.valueOf(arg0));
        driver.pageShouldContain(String.valueOf(arg0) + " " + description + " Edit");
    }


    @And("^「Edit」というキャプションのボタンが(\\d+)件毎に表示される事$")
    public void editというキャプションのボタンが件毎に表示される事(int arg0) {

        // table class: table table-responsive table-bordered を取得
        WebElement questionTable = driver.findElement("questionTable");
        List<WebElement> trs = questionTable.findElements(By.tagName("tr"));

        // forとかで Edit文字があることをチェック
        for (WebElement tr: trs) {
            List<WebElement> tds = tr.findElements(By.tagName("td"));

            String expect = "Edit";

            // Edit 列を取得
            String actual = tds.get(2).getText();
            assertEquals(expect,actual);
        }
    }
    @And("^QuestionListの一覧は、QuestionID昇順で表示される事$")
    public void questionlistの一覧はQuestionID昇順で表示される事() {

        // table class: table table-responsive table-bordered を取得
        WebElement questionTable = driver.findElement("questionTable");
        List<WebElement> trs = questionTable.findElements(By.tagName("tr"));

        // forとかで Edit文字があることをチェック
        for (int i=0; trs.size() > i; i++) {
            List<WebElement> tds = trs.get(i).findElements(By.tagName("td"));

            String expect = String.valueOf(i+1);

            // Edit 列を取得
            String actual = tds.get(0).getText();
            assertEquals(expect,actual);
        }

    }

    @Given("^QuestionListが開かれ、Questionが登録されている$")
    public void questionlistが開かれ_Questionが登録されている() {

        site.visit("onlinetest/question_list.jsp");

        // ここでデータのDelete-Insertが必要かも
    }

    @When("^Question(\\d+)のレコードの「Edit」ボタンを押した時$")
    public void question_のレコードの_Edit_ボタンを押した時(int arg1) {
        // table class: table table-responsive table-bordered を取得
        WebElement questionTable = driver.findElement("questionTable");
        List<WebElement> trs = questionTable.findElements(By.tagName("tr"));
        List<WebElement> tds = trs.get(arg1 - 1).findElements(By.tagName("td"));

        // Edit をクリック
        tds.get(2).click();
    }

    @Then("^Question(\\d+)の編集画面に遷移する$")
    public void question_の編集画面に遷移する(int arg1) {
        String expect = site.baseUrl() + "/onlinetest/edit_question?question_id=2";
        driver.expectRedirect(expect);
    }
}
