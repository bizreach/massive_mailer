Feature: Question List

  @now
  Scenario: Questionがない時の一覧表示
    Given Questionに一件も登録されていない
    When 左部のサイドバーのQuestionListをクリックしたとき
    Then QuestonList画面にヘッダのみ表示される

  @now
  Scenario: クエスチョンがある時の一覧の表示
    Given Questionが10件登録されている、1件目に「Scrumの用語はどれか」というDescriptionで登録する
    When 左部のサイドバーのQuestionListをクリックしたとき
    Then 1件目のQuestionIDが"1"と表示しされている事
    And QuestionListが10件表示され、Questionが1行ずつ図のように表示される
    And 「QuestionID」「Description」「Edit」カラムが表示されている事
    And 1件目のDescriptionに"Scrumの擁護はどれか？"と表示されるされている事
    And 「Edit」というキャプションのボタンが1件毎に表示される事
    And QuestionListの一番上にはQuestionIDの昇順で表示される事

