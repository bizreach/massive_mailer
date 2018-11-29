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
      | 2 | あ  |
      | 3 | い  |
      | 4 | う  |
      | 5 | え  |
      | 6 | お  |
      | 7 | か  |
      | 8 | き  |
      | 9 | く  |
      | 10 | け |
    When 左部のサイドバーのQuestionListをクリックしたとき
    Then 1件目のQuestionのQuestionIDが"1"、Descriptionに"Scrumの用語はどれか？"と表示されている事
    And QuestionListが10件表示され、Questionが1行ずつ図のように表示される
    And 「Edit」というキャプションのボタンが1件毎に表示される事
    And QuestionListの一覧は、QuestionID昇順で表示される事

