package ru.netology.selenium;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.openqa.selenium.By.*;

class CallbackTest {
    private WebDriver driver;

    @BeforeAll
    static void setUpAll() {
        System.setProperty("webdriver.chrome.driver", "driver//chromedriver.exe");
    }

    @BeforeEach
    void setUp() {


        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        driver = new ChromeDriver();
    }


    @AfterEach
    void tearDown() {
        driver.quit();
        driver = null;
    }


    @Test
    void shouldTest() {
        driver.get("http://localhost:9999/");
        driver.findElement(cssSelector("[class='input__control'][type='text']")).sendKeys("Пупкин Вася");
        driver.findElement(cssSelector("[class='input__control'][type='tel']")).sendKeys("+79967466215");
        driver.findElement(cssSelector(".checkbox__box")).click();
        driver.findElement(cssSelector("button")).click();
        String message = driver.findElement(cssSelector(".paragraph")).getText();
        assertEquals("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.", message.trim());


    }


    @Test
    void shouldTestError1() {
        driver.get("http://localhost:9999/");
        driver.findElement(cssSelector("[class='input__control'][type='text']")).sendKeys("");
        driver.findElement(cssSelector("button")).click();
        String message = driver.findElement(cssSelector(".input__sub")).getText();
        assertEquals("Поле обязательно для заполнения", message.trim());


    }


    @Test
    void shouldTestErrorName() {
        driver.get("http://localhost:9999/");
        driver.findElement(cssSelector("[class='input__control'][type='text']")).sendKeys("Nika - Mironova");
        driver.findElement(cssSelector("button")).click();
        String message = driver.findElement(cssSelector(".input__sub")).getText();
        assertEquals("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.", message.trim());

    }

    @Test
    void shouldTestErorrNumber() {
        driver.get("http://localhost:9999/");
        driver.findElement(cssSelector("[class='input__control'][type='text']")).sendKeys("Пупкин Вася");
        driver.findElement(cssSelector("[class='input__control'][type='tel']")).sendKeys("+79");
        driver.findElement(cssSelector(".checkbox__box")).click();
        driver.findElement(cssSelector("button")).click();
        String message = driver.findElement(cssSelector(".input_invalid .input__sub")).getText();
        assertEquals("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.", message.trim());

    }


    @Test
    void shouldTestEmptyField() {
        driver.get("http://localhost:9999/");
        driver.findElement(cssSelector("[class='input__control'][type='text']")).sendKeys("");
        driver.findElement(cssSelector("[class='input__control'][type='tel']")).sendKeys("");
        driver.findElement(cssSelector(".checkbox__box")).click();
        driver.findElement(cssSelector("button")).click();
        String message = driver.findElement(cssSelector(".input_invalid .input__sub")).getText();
        assertEquals("Поле обязательно для заполнения", message.trim());


    }
    @Test
    void shouldTestEmpty() {
        driver.get("http://localhost:9999/");
        driver.findElement(cssSelector("[class='input__control'][type='text']")).sendKeys("");
        driver.findElement(cssSelector("[class='input__control'][type='tel']")).sendKeys("+79967466215");
        driver.findElement(cssSelector(".checkbox__box")).click();
        driver.findElement(cssSelector("button")).click();
        String message = driver.findElement(cssSelector(".input_invalid .input__sub")).getText();
        assertEquals("Поле обязательно для заполнения", message.trim());


    }


    @Test
    void shouldTestUserAgreement() {
        driver.get("http://localhost:9999/");
        driver.findElement(cssSelector("[class='input__control'][type='text']")).sendKeys("");
        driver.findElement(cssSelector("[class='input__control'][type='tel']")).sendKeys("+79967466215");
        driver.findElement(cssSelector(".checkbox__box")).click();
        driver.findElement(cssSelector("button")).click();
        String message = driver.findElement(cssSelector(".checkbox__text")).getText();
        assertEquals("Я соглашаюсь с условиями обработки и использования моих персональных данных и разрешаю сделать запрос в бюро кредитных историй", message.trim());


    }
}