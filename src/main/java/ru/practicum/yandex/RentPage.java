package ru.practicum.yandex;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RentPage {
    private final WebDriver driver;
    //Локатор для поля когда привезти самокат
    private final By deliveryDate = By.cssSelector("div.react-datepicker__input-container>input");

    // Локатор для треугольника в поле Срок аренды
    private final By rentTime = By.className("Dropdown-arrow");

    //Локатор для полей с указанием суток (в выпадающем списке после клика на треугольник)
    private final By rentDay = By.className("Dropdown-option");

    //Локатор для кнорки Заказть
    private final By orderScooterButton = By.xpath(".//div[@class='Order_Buttons__1xGrp']//button[text()='Заказать']");
    //Локатор для кнопки Да
    private final By getOrderYesButton = By.xpath(".//button[text()='Да']");
    // Информация подтверждения заказа
    private final By orderInfo = By.className("Order_ModalHeader__3FDaJ");
    public RentPage (WebDriver driver){
        this.driver=driver;
    }
    public void setRentDate(String newRentDate) {
        driver.findElement(deliveryDate).click();
        driver.findElement(deliveryDate).clear();
        driver.findElement(deliveryDate).sendKeys(newRentDate);
    }
    public void clickRentTime() {
        driver.findElement(rentTime).click();
    }

    public void clickRentTimeDays(int day) {
        driver.findElements(rentDay).get(day).click();
    }

    public void clickOrderButton() {
        driver.findElement(orderScooterButton).click();
    }
    public void clickOrderYesButton() {
        driver.findElement(getOrderYesButton).click();
    }

    public String isOrderDisplayed() {
        return driver.findElement(orderInfo).getText();
    }

    public void fillRentPage(String rentDate, int rentTimeDays) {
        this.setRentDate(rentDate);
        this.clickRentTime();
        this.clickRentTimeDays(rentTimeDays);
        this.clickOrderButton();
        this.clickOrderYesButton();
    }
}
