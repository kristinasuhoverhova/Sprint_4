package ru.practicum.yandex;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class OrderPage {
    private final WebDriver driver;
    //Локатор для поля имя
    private final By name = By.xpath(".//input[@placeholder='* Имя']");
    //Локатор для поля фамилия
    private final By surName = By.xpath(".//input[@placeholder='* Фамилия']");
    //Локатор для поля адрес
    private final By adress = By.xpath(".//input[contains(@placeholder,'Адрес')]");
    //Локатор для поля метро
    private final By metroButton = By.className("select-search__input");
    // Список станций метро
    private final By metroStation = By.className("select-search__row");
    //Локатор для поля телефон
    private final By phoneNumber = By.xpath(".//input[contains(@placeholder,'Телефон')]");
    //Локатор для кнопки далее
    private final By nextButton = By.xpath(".//button[text()='Далее']");
    public OrderPage(WebDriver driver){
        this.driver = driver;
    }
    public void setName(String newName) {
        driver.findElement(name).click();
        driver.findElement(name).clear();
        driver.findElement(name).sendKeys(newName);
    }
    public void setSurName(String newSurName) {
        driver.findElement(surName).click();
        driver.findElement(surName).clear();
        driver.findElement(surName).sendKeys(newSurName);
    }
    public void setAdres(String newAdres) {
        driver.findElement(adress).click();
        driver.findElement(adress).clear();
        driver.findElement(adress).sendKeys(newAdres);
    }
    public void clickButtonMetro() {
        driver.findElement(metroButton).click();
    }
    public void setMetro(int index) {
        WebElement element = driver.findElements(metroStation).get(index);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
        element.click();
    }
    public void setPhoneNumber(String newPhoneNumber) {
        driver.findElement(phoneNumber).click();
        driver.findElement(phoneNumber).clear();
        driver.findElement(phoneNumber).sendKeys(newPhoneNumber);
    }
    public void clickButtonNext() {
        driver.findElement(nextButton).click();
    }
    public void fillOrderPage(String name, String surName, String address, String phoneNumber, int metroStation) {
        this.setName(name);
        this.setSurName(surName);
        this.setAdres(address);
        this.setPhoneNumber(phoneNumber);
        this.clickButtonMetro();
        this.setMetro(metroStation);
        this.clickButtonNext();
    }
}
