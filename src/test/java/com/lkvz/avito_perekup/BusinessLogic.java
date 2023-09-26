package com.lkvz.avito_perekup;

import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.refresh;
import static com.codeborne.selenide.Selenide.sleep;

public class BusinessLogic extends BaseSetUp {

    @Test
    public void logic(){
        while(true) {
            FilterPage.selectWithinARadius();
            FilterPage.results();
            sleep(30000);
            Selenide.clearBrowserCookies();
            refresh();
        }
    }
}
