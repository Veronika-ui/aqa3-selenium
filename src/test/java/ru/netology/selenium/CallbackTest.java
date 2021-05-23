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
        System.setProperty("webdriver.chrome.driver", "driver/chromedriver");
    }

    @BeforeEach
    void setUp() {

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
    }


    @AfterEach
    void tearDown() {
        driver.quit();
        driver = null;
    }


    @Test
    void shouldTest() {
        driver.get("http://localhost:9999/");
        driver.findElement(cssSelector("[data-test-id=name] input ")).sendKeys("Пупкин Вася");
        driver.findElement(cssSelector("[data-test-id='phone'] input")).sendKeys("+79967466215");
        driver.findElement(cssSelector("[data-test-id=agreement]")).click();
        driver.findElement(cssSelector(".button__content")).click();
        String text = driver.findElement(cssSelector("[data-test-id=order-success]")).getText();
        assertEquals("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.", text.trim());


    }


    @Test
    void shouldTestError() {
        driver.get("http://localhost:9999/");
        driver.findElement(cssSelector("[data-test-id=name] input ")).sendKeys("");
        driver.findElement(cssSelector("[data-test-id='phone'] input")).sendKeys("");
        driver.findElement(cssSelector("[data-test-id=agreement]")).click();
        driver.findElement(cssSelector(".button__content")).click();
        String text = driver.findElement(cssSelector("[data-test-id=name].input_invalid .input__sub")).getText();
        assertEquals("Поле обязательно для заполнения", text.trim());


    }


    @Test
    void shouldTestErrorName() {
        driver.get("http://localhost:9999/");
        driver.findElement(cssSelector("[data-test-id=name] input ")).sendKeys("Vero Nika");
        driver.findElement(cssSelector("[data-test-id='phone'] input")).sendKeys("+79967466215");
        driver.findElement(cssSelector("[data-test-id=agreement]")).click();
        driver.findElement(cssSelector(".button__content")).click();
        String text = driver.findElement(cssSelector("[data-test-id=name].input_invalid .input__sub")).getText();
        assertEquals("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.", text.trim());

    }


    @Test
    void shouldTestErorrNumber() {
        driver.get("http://localhost:9999/");
        driver.findElement(cssSelector("[data-test-id=name] input ")).sendKeys("Пупкин Вася");
        driver.findElement(cssSelector("[data-test-id='phone'] input")).sendKeys("+8765");
        driver.findElement(cssSelector("[data-test-id=agreement]")).click();
        driver.findElement(cssSelector(".button__content")).click();
        String text = driver.findElement(cssSelector(".input_invalid .input__sub")).getText();
        assertEquals("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.", text.trim());

    }


    @Test
    void shouldTestEmptyField() {
        driver.get("http://localhost:9999/");
        driver.findElement(cssSelector("[data-test-id=name] input ")).sendKeys("");
        driver.findElement(cssSelector("[data-test-id='phone'] input")).sendKeys("");
        driver.findElement(cssSelector("[data-test-id=agreement]")).click();
        driver.findElement(cssSelector(".button__content")).click();
        String text = driver.findElement(cssSelector("[data-test-id=name].input_invalid .input__sub")).getText();
        assertEquals("Поле обязательно для заполнения", text.trim());


    }
    @Test
    void shouldTestEmpty() {
        driver.get("http://localhost:9999/");
        driver.findElement(cssSelector("[data-test-id=name] input ")).sendKeys("");
        driver.findElement(cssSelector("[data-test-id='phone'] input")).sendKeys("+79967466215");
        driver.findElement(cssSelector("[data-test-id=agreement]")).click();
        driver.findElement(cssSelector(".button__content")).click();
        String text = driver.findElement(cssSelector("[data-test-id=name].input_invalid .input__sub.input__sub")).getText();
        assertEquals("Поле обязательно для заполнения", text.trim());


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