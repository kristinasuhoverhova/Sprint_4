package ru.yandex.practicum;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import ru.practicum.yandex.MainPage;
import ru.practicum.yandex.OrderPage;
import ru.practicum.yandex.RentPage;
import java.util.concurrent.TimeUnit;
import static org.junit.Assert.assertTrue;
@RunWith(Parameterized.class)
public class TestOrderScooter {
    WebDriver driver;
    private boolean flag;
    private final String name;
    private final String surName;
    private final String adress;
    private final String pnoneNumber;
    private final int metroStation;
    private final String deliveryDate;
    private final int deliveryDay;
    public TestOrderScooter(Boolean flag, String name, String surName, String adress, String pnoneNumber, int metroStation, String deliveryDate, int deliveryDay) {
        this.flag = flag;
        this.name = name;
        this.surName = surName;
        this.adress = adress;
        this.pnoneNumber = pnoneNumber;
        this.metroStation = metroStation;
        this.deliveryDate = deliveryDate;
        this.deliveryDay = deliveryDay;
    }
    @Parameterized.Parameters
    public static Object[][] getOrderData() {
        return new Object[][]{
                {true, "Кристина", "Суховерхова", "Прошлякова 13", "89162733062", 1, "11.11.2023", 3},
                {false, "Максим", "Суховерхов", "Гагарина 14", "89162235899", 2, "17.02.2023", 4}
        };
    }
    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox", "--disable-dev-shm-usage");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://qa-scooter.praktikum-services.ru/");
    }
    /* @Before
     public void setUp() {
         WebDriverManager.firefoxdriver().setup();
             FirefoxOptions options = new FirefoxOptions();
             options.addArguments("--no-sandbox", "--disable-dev-shm-usage");
             driver = new FirefoxDriver(options);
         driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
         driver.manage().window().maximize();
         driver.get("https://qa-scooter.praktikum-services.ru/");
     }*/
    @Test
    public void orderScooter() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickOrderButton(flag);
        OrderPage orderPage = new OrderPage(driver);
        orderPage.fillOrderPage(name, surName, adress, pnoneNumber, metroStation);
        RentPage rentPage = new RentPage(driver);
        rentPage.fillRentPage(deliveryDate, deliveryDay);
        Assert.assertTrue(rentPage.isOrderDisplayed().contains("Заказ оформлен"));
    }
    @After
    public void tearDown() {
        driver.quit();
    }
}

