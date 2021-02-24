package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import entity.Purchase;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ShoppingListPage {

    protected WebDriver driver;
    protected WebDriverWait wait;

    private static final List<Purchase> purchaseList = new ArrayList<>();

    public static List<Purchase> getPurchaseList() {
        return purchaseList;
    }

    public ShoppingListPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(this.driver, 5);
    }

    public void openUrl(String url) {
        driver.get(url);
    }

    public void isPageInitialized() {
        JavascriptExecutor input = (JavascriptExecutor) driver;
        assertThat(input.executeScript("return document.readyState").equals("complete")).isEqualTo(true);
    }

    public void readDataTable() {
        int rowCount = driver.findElements(By.xpath("//tbody/tr")).size();
        System.out.println("\nЧИТАЕМ ДАННЫМ ИЗ ТАБЛИЦЫ НА https://checkme.kavichki.com/");
        System.out.printf("%20s %20s %20s%n%n", "Что купить", "Количество", "Стоимость, кр");
        for (int i = 1; i <= rowCount; i++) {
            String name = driver.findElement(By.xpath("//tbody/tr[" + i + "]/td[1]")).getText();
            int number = Integer.parseInt(driver.findElement(By.xpath("//tbody/tr[" + i + "]/td[2]")).getText());
            double amount = Double.parseDouble(driver.findElement(By.xpath("//tbody/tr[" + i + "]/td[3]")).getText());
            System.out.printf("%20s %20d %20.2f%n", name, number, amount);
            Purchase temp = new Purchase();
            temp.setName(name);
            temp.setNumber(number);
            temp.setAmount(amount);
            purchaseList.add(temp);
        }
    }

    public void addDataToWebTable(String name, String number, String amount) {
        System.out.println("\n\nДОБАВИМ СТРОЧКУ В ТАБЛИЦУ НА https://checkme.kavichki.com/\n");
        System.out.printf("%20s %20s %20s%n", "Что купить", "Количество", "Стоимость, кр");
        System.out.printf("%20s %20d %20.2f%n", name, Integer.parseInt(number), Double.parseDouble(amount));
        driver.findElement(By.id("open")).click();
        driver.findElement(By.id("name")).sendKeys(name);
        driver.findElement(By.id("count")).sendKeys(number);
        driver.findElement(By.id("price")).sendKeys(amount);
        {
            WebElement element = driver.findElement(By.id("add"));
            Actions builder = new Actions(driver);
            builder.moveToElement(element).click().perform();
        }
    }

    public void exist(String str) {
        Assert.assertTrue("Элемент не отображается!!! Ошибка", driver.findElement(By.xpath("//*[contains(text(), '" + str + "')]")).isDisplayed());
    }

    public void notExist(String str) {
        Assert.assertTrue("Элемент не отображается!!! Ошибка", driver.findElement(By.xpath("//*[contains(text(), '" + str + "')]")).isDisplayed());
    }

    public void clickDelete(String str) {
        driver.findElement(By.xpath("//*[contains(text(), '"+str+"')]/following-sibling::td/child::a[@class='delete']")).click();
    }

    public void checkRightFillingCells(String arg0, String arg1, String arg2, String arg3) {

        //td[contains(text(), 'test')]/parent::tr
        //td[contains(text(), 'test')]/parent::tr/td[3]

    }
}