Feature: Задание 1-4: Несколько функциональных тестов

  @UI
  Scenario: Проверка добавления данных
    Given Open start page "https://checkme.kavichki.com/"
    And   Start page is opened
    And   Add data to web table: name "Огурцы" number "3" amount "150"
    Then  Check is row "Огурцы" exist

  @UI
  Scenario: Проверка соответствия добавленных данных столбцам
    Given Open start page "https://checkme.kavichki.com/"
    And   Start page is opened
    And   Add data to web table: name "Огурцы" number "3" amount "150"
    Then  Check is row "Огурцы" contains name = "Огурцы" number = "3" amount = "150"

  @UI
  Scenario: Проверка удаления записей по умолчанию из таблицы
    Given Open start page "https://checkme.kavichki.com/"
    And   Start page is opened
    And   Delete row "Межгалактический щит"
    Then  Check is not row "Межгалактический щит" exist