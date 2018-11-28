Feature: Question List

  @now
  Scenario: Questionがない時の一覧表示
    Given Questionに一件も登録されていない
    When 左部のサイドバーのQuestionListをクリックしたとき
    Then QuestonList画面にヘッダのみ表示される