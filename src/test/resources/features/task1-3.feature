Feature: Задание 1-3: Проверка работы с БД

  Scenario: Добавить запись на странице и сравнить с тем что добавляли
    Given Open start page "https://checkme.kavichki.com/"
    And   Start page is opened
    #And   Read data table from webpage
    #And   Insert data to datatable
    # поправим запись в таблице
    And   Update one cell
    And   Read data from database
    And   Read data table from webpage
    And   Check data is incorrect by rows
    And   Check data is incorrect by cells
    #Then  Delete tables