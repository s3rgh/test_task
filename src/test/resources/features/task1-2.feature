Feature: Задание 1-2: Проверка работы с БД

  Scenario: Добавить запись на странице и сравнить с тем что добавляли
    Given Open start page "https://checkme.kavichki.com/"
    And   Start page is opened
    And   Read data table from webpage
    #And   Insert data to datatable
    And   Read data from database
    # добавим строчку на страницу
    And   Add data to web table: name "Огурцы" number "3" amount "150"
    And   Read data table from webpage
    And   Check data is incorrect by rows
    And   Check data is incorrect by cells
    #Then  Delete tables