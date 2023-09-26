package com.lkvz.avito_perekup;

import com.codeborne.selenide.Configuration;

import com.codeborne.selenide.Selenide;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;

import org.openqa.selenium.chrome.ChromeOptions;

import static com.codeborne.selenide.Selenide.*;

public class BaseSetUp {

    String
            uvarovo400kR100km = "/uvarovo/avtomobili/do-400000-rubley-" +
            "ASgCAgECAUXGmgwWeyJmcm9tIjowLCJ0byI6NDAwMDAwfQ?cd=1&f=" +
            "ASgBAgECAUTyCrCKAQJF~AIZeyJmcm9tIjo4OTgsInRvIjozMjM1OTY" +
            "wfcaaDBZ7ImZyb20iOjAsInRvIjo0MDAwMDB9&radius=100&s=104&" +
            "searchRadius=100&user=1",
            gribanovskiy1000kR200km = "/gribanovskiy/avtomobili?cd=1&f=" +
                    "ASgBAQECA0TyCrCKAYYUyOYB~vAP6Lv3AgFA9sQNFL6wOgJF~" +
                    "AIZeyJmcm9tIjo4OTksInRvIjozMjM1OTYwfcaaDBd7ImZyb20i" +
                    "OjAsInRvIjoxMDAwMDAwfQ&radius=200&s=104&searchRadius=200&user=1",
            voronezh1200kR50km = "/voronezh/avtomobili?cd=1&f=" +
                    "ASgBAQECA0TyCrCKAYYUyOYB~vAP6Lv3AgFA9sQNFL6wOgJF~" +
                    "AIZeyJmcm9tIjo4OTksInRvIjozMjM1OTYwfcaaDBd7ImZyb20iOj" +
                    "AsInRvIjoxMjAwMDAwfQ&radius=200&s=104&searchRadius=50&user=1",
            voronezh1200kR200km = "/voronezh/avtomobili?cd=1&f=" +
                    "ASgBAQECA0TyCrCKAYYUyOYB~vAP6Lv3AgFA9sQNFL6wOgJF~" +
                    "AIZeyJmcm9tIjo4OTksInRvIjozMjM1OTYwfcaaDBd7ImZyb20iOj" +
                    "AsInRvIjoxMjAwMDAwfQ&radius=200&s=104&searchRadius=200&user=1";

    @BeforeAll
    public static void setUp() {
/*
        Configuration.remote = "http://109.106.139.39:4444/wd/hub";
*/
        WebDriverManager.chromedriver().setup();
        Configuration.browserVersion = "109";
        Configuration.browser = "chrome";
        Configuration.baseUrl = "https://www.avito.ru";
        Configuration.pageLoadStrategy = "eager";
        Configuration.headless = true;

        Configuration.browserCapabilities = new ChromeOptions()
                .addArguments("--no-sandbox")
                .addArguments("--disable-infobars")
                .addArguments("--disable-popup-blocking")
                .addArguments("--disable-notifications")
                .addArguments("--lang=en-US")
                .setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
    }

    @BeforeEach
    public void init() {
        open(voronezh1200kR200km);

    }


    @AfterEach
    public void attachmentAdd() {
        Selenide.closeWebDriver();
    }

    @AfterAll
    public static void tearDown() {
    }

}
