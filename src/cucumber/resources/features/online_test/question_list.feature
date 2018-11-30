Feature: Question List

  Scenario: Questionがない時の一覧表示
    Given Questionに一件も登録されていない
    When 左部のサイドバーのQuestionListをクリックしたとき
    Then QuestonList画面にヘッダのみ表示される

  Scenario: クエスチョンがある時の一覧の表示
    Given Questionが10件登録されている、1件目に「Scrumの用語はどれか？」というDescriptionで登録する
      | 1 | Scrum  |
      | 2 | a  |
      | 3 | b  |
      | 4 | c  |
      | 5 | d  |
      | 6 | e  |
      | 7 | f  |
      | 8 | g  |
      | 9 | h  |
      | 10 | i |
    When 左部のサイドバーのQuestionListをクリックしたとき
    Then 1件目のQuestionのQuestionIDが"1"、Descriptionに"Scrum"と表示されている事
    And QuestionListが10件表示され、Questionが1行ずつ図のように表示される
    And 「Edit」というキャプションのボタンが1件毎に表示される事
    And QuestionListの一覧は、QuestionID昇順で表示される事

  @developing
  Scenario: 任意のクエスチョンの編集画面上に遷移する
    Given QuestionListが開かれ、Questionが登録されている
      | 1 | Scrum  |
      | 2 | a  |
      | 3 | b  |
      | 4 | c  |
      | 5 | d  |
      | 6 | e  |
      | 7 | f  |
      | 8 | g  |
      | 9 | h  |
      | 10 | i |
    When Question2のレコードの「Edit」ボタンを押した時
    Then Question2の編集画面に遷移する
