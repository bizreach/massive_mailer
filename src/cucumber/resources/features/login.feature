Feature: Login
  User should be able to login
  if email address and password are valid

  Background:
    Given There are 3 courses
    Given There are 5 contact people
    And I visit "CSD-1" detail page from course list page
    And I enroll participants to "CSD-1" from course detail page
      | JohnSmith@mail.com	Tom	Smith	CS	Singapore |
      | JaneDoe@mail.com	John	Fisher	CS	Singapore |
    And I visit "CSD-2" detail page from course list page
    And I enroll participants to "CSD-2" from course detail page
      | john@example.com	john	jon	CS	Singapore    |
      | JaneDoe@mail.com	John	Fisher	CS	Singapore |

  Scenario Outline: login
    Given Visit Login Page
    Given There are users as bellow
      | mary@example.com | abcd1234 |
    Given Fill form with "<email>" and "<password>"
    When I click login button
    Then I should move to page with url "<url>"
    And Login failed message is <message>
    Examples:
      | email               | password | url                   | message |
      | mary@example.com    | abcd1234 | course_list.jsp       | hidden  |
      | mary@example.com    | hogehoge | login.jsp?status=fail | shown   |
      | unknown@example.com | abcd1234 | login.jsp?status=fail | shown   |

  Scenario Outline: login fail when user's password is not initialized
    Given Visit Login Page
    Given There is a user with "mary@example.com" but password initialize is undone
    Given Fill form with "<email>" and "<password>"
    When I click login button
    Then I should move to page with url "<url>"
    And Login failed message is <message>
    Examples:
      | email               | password | url                   | message |
      | mary@example.com    | abcd1234 | login.jsp?status=fail | shown   |

  Scenario Outline: Courses List after Login
    Given Visit Login Page
    Given There are users as bellow
      | JohnSmith@mail.com | abcd1234 |
      | JaneDoe@mail.com   | abcd1001 |
      | john@example.com   | abcd1002 |
      | Bobb@example.com   | abcd1003 |
    Given Fill form with "<email>" and "<password>"
    When I click login button
    Then Show courses list "<courses>"

    Examples:
      | email              | password | courses     |
      | JohnSmith@mail.com | abcd1234 | CSD-1       |
      | JaneDoe@mail.com   | abcd1001 | CSD-1,CSD-2 |
      | john@example.com   | abcd1002 | CSD-2       |
      | Bobb@example.com   | abcd1003 |             |

  Scenario Outline: Preserve login info after navigation
    Given Visit Login Page
    Given There are users as bellow
      | JaneDoe@mail.com | abcd1001 |
      | john@example.com | abcd1002 |
      | Bobb@example.com | abcd1003 |
    Given Fill form with "<email>" and "<password>"
    And I click login button
    And I move to top page
    When I move to course list page
    Then Show courses list "<courses>"

    Examples:
      | email            | password | courses     |
      | JaneDoe@mail.com | abcd1001 | CSD-1,CSD-2 |
      | john@example.com | abcd1002 | CSD-2       |
      | Bobb@example.com | abcd1003 |             |

    Scenario: Courses List no login
      When I move to course list page
      Then Show all courses list "CSD-1,CSD-2,CSD-3"