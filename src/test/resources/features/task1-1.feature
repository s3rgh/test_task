Feature: Задание 1-1 Проверка работы с БД Сравнить записи на странице с содержимым в БД
  @UI
  Scenario: Сравнить записи на странице с содержимым в БД
    Given Open start page "https://checkme.kavichki.com/"
    And   Start page is opened
    And   Read data table from webpage
    And   Insert data to datatable
    And   Read data from database
    And   Check data is correct by rows
    And   Check data is correct by cells