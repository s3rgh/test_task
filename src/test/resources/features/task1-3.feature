Feature: Задание 1-3: Поправить ячейку в БД и сравнить с тем что на странице
  @UI
  Scenario: Поправить ячейку в БД и сравнить с тем что на странице
    Given Open start page "https://checkme.kavichki.com/"
    And   Start page is opened
    # поправим запись в таблице
    And   Update one cell
    And   Read data from database
    And   Read data table from webpage
    And   Check data is incorrect by rows
    And   Check data is incorrect by cells