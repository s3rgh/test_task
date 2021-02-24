Feature: Задание 1-2: Добавить запись на странице и сравнить с тем что добавляли
  @UI
  Scenario: Добавить запись на странице и сравнить с тем что лежит в БД
    Given Open start page "https://checkme.kavichki.com/"
    And   Start page is opened
    And   Read data table from webpage
    And   Read data from database
    # добавим строчку на страницу
    And   Add data to web table: name "Огурцы" number "3" amount "150"
    And   Read data table from webpage
    And   Check data is incorrect by rows
    And   Check data is incorrect by cells