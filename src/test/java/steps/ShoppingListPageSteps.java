package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.ShoppingListPage;
import repository.ShoppingListBD;

public class ShoppingListPageSteps extends TestBase {

    ShoppingListPage shoppingListPage;
    ShoppingListBD shoppingListBD = new ShoppingListBD();

    @Before
    public void start() {
        initialize();
        shoppingListPage = new ShoppingListPage(webDriver);
    }

    @After
    public void stop() {
        tearDown();
    }

    @When("Open start page {string}")
    public void openPage(String url) {
        shoppingListPage.openUrl(url);
    }

    @And("Start page is opened")
    public void pageIsOpened() {
        shoppingListPage.isPageInitialized();
    }

    @And("Read data table from webpage")
    public void readDataTableFromWebpage() {
        shoppingListPage.readDataTable();
    }

    @And("Create table from web page")
    public void createTableFromWebPage() {
        shoppingListBD.initializeTable();
    }

    @And("Insert data to datatable")
    public void insertDataToDatatable() {
        shoppingListBD.insertDataToDataTable();
    }

    @And("Read data from database")
    public void readDataFromDatabase() {
        shoppingListBD.getAllRows();
    }

    @Then("Check data is correct by rows")
    public void checkIsDataCorrectByRows() {
        shoppingListBD.isDataCorrect1();
    }

    @Then("Check data is incorrect by rows")
    public void checkIsDataIncorrectByRows() {
        shoppingListBD.isDataIncorrect1();
    }

    @Then("Check data is correct by cells")
    public void checkIsDataCorrectByCells() {
        shoppingListBD.isDataCorrect2();
    }

    @Then("Check data is incorrect by cells")
    public void checkIsDataIncorrectByCells() {
        shoppingListBD.isDataIncorrect2();
    }

    @Then("Delete tables")
    public void deleteTable() {
        shoppingListBD.deleteTable();
    }

    @And("Add data to web table: name {string} number {string} amount {string}")
    public void addDataToWebTable(String name, String number, String amount) {
        shoppingListPage.addDataToWebTable(name, number, amount);
    }

    @Then("Check is row {string} exist")
    public void isExist(String str) {
        shoppingListPage.exist(str);
    }

    @Then("Check is not row {string} exist")
    public void isNotExist(String str) {
        shoppingListPage.notExist(str);
    }

    @And("Update one cell")
    public void updateOneCell() {
        shoppingListBD.update();
    }

    @And("Delete row {string}")
    public void delete(String str) {
        shoppingListPage.clickDelete(str);
    }

    @Then("Check is row {string} contains name = {string} number = {string} amount = {string}")
    public void checkIsRowContainsNameNumberAmount(String arg0, String arg1, String arg2, String arg3) {
        shoppingListPage.checkRightFillingCells(arg0, arg1, arg2, arg3);
    }
}
