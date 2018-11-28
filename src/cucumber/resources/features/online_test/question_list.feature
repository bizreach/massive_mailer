Feature: Question List

  @now
  Scenario: Questionがない時の一覧表示
    Given Questionに一件も登録されていない
    When 左部のサイドバーのQuestionListをクリックしたとき
    Then QuestonList画面にヘッダのみ表示される

  @now
  Scenario: クエスチョンがある時の一覧の表示
    Given Questionが10件登録されている、1件目に「Scrumの用語はどれか？」というDescriptionで登録する
      | 1 | Scrumの用語はどれか？  |
      | 2 | Scrumの用語はどれか？  |
#      | 3 Scrumの用語はどれか？  |
#      | 4 Scrumの用語はどれか？  |
#      | 5 Scrumの用語はどれか？  |
#      | 6 Scrumの用語はどれか？  |
#      | 7 Scrumの用語はどれか？  |
#      | 8 Scrumの用語はどれか？  |
#      | 9 Scrumの用語はどれか？  |
#      | 10 Scrumの用語はどれか？ |
    When 左部のサイドバーのQuestionListをクリックしたとき
    Then 1件目のQuestionIDが"1"と表示しされている事
    And QuestionListが10件表示され、Questionが1行ずつ図のように表示される
    And 「QuestionID」「Description」「Edit」カラムが表示されている事
    And 1件目のDescriptionに"Scrumの用語はどれか？"と表示されるされている事
    And 「Edit」というキャプションのボタンが1件毎に表示される事
    And QuestionListの一番上にはQuestionIDの昇順で表示される事

