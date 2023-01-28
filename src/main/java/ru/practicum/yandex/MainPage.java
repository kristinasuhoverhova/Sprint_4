package ru.practicum.yandex;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage {
    private final WebDriver driver;

    // Кнопка заказать сверху страницы
    private final By orderButtonTop = By.xpath(".//div[@class='Header_Nav__AGCXC']//button[@class='Button_Button__ra12g']");

    //Кнопка заказать снизу страницы

     private final By orderButtonDown = By.xpath(".//div[@class='Home_FinishButton__1_cWm']//button");

 // Блок с FAQ
    private final By questionButton = By.xpath(".//div[@class='accordion__button']");


    public MainPage(WebDriver driver) {

        this.driver=driver;
    }
   public void clickOrderButton(boolean flag) {
       if (flag) {
           driver.findElement(orderButtonTop).click();
       } else {
           WebElement element = driver.findElement(orderButtonDown);
           ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
           element.click();
       }
   }
    public void clickQuestion(int index) {
        driver.findElements(questionButton).get(index).click();
    }
    public Boolean isAnswerVisible(int index) {
        By element = By.id("accordion__panel-" + index);
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(element));
        return driver.findElement(element).isDisplayed();
    }
    public String getQuestionText(int index) {
        return driver.findElements(questionButton).get(index).getText();


    }
    public String getAnswerText(int index) {
        By element = By.xpath(".//div[@id='accordion__panel-" + index + "']/p");
        return driver.findElement(element).getText();
    }



}
