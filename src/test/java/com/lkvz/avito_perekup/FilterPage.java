package com.lkvz.avito_perekup;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.restassured.http.ContentType;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static io.restassured.RestAssured.given;

public class FilterPage {

    private static SelenideElement
            checkbox = $("[name=\"localPriority\"]"),
            search = $("[data-marker=\"search-filters/submit-button\"]"),
            datePublish = $("[data-marker=\"item-date\"]");


    private static ElementsCollection
            ads = $$(".iva-item-content-rejJg");

    private static List<String>
            hrefs = new ArrayList<>(),
            locations = new ArrayList<>();

    private static String
            chatId = "-1001966423896",
            tokenBot = "bot6043291061:AAEYJKiKm-y8q7iJ0HlEM1SBkywFlJdgHEs";

    static {
/*
        locations.add("Воронеж");
        locations.add("Липецк");
*/
        locations.add("Тамбов");
    }



    public static void selectWithinARadius(){
        if(true){
            if (!checkbox.isSelected()) {
                checkbox.parent().click();
            }
            search.shouldBe(visible).click();
        }
    }

    public static void results(){

        for (SelenideElement ad:ads) {

            String location = ad.$(".geo-root-zPwRk p span").getText();

            String fullTime = ad.$("[data-marker=\"item-date\"]")
                    .getText();

            String strTime = fullTime.split(" ")[0];

            int timeInteger;

            if(strTime.length() < 2) {
                timeInteger = Integer.parseInt(strTime);
            }else {
                return;
            }

            if((fullTime.contains("секунд")||
                    fullTime.contains("минут")) &&
                    timeInteger <= 20 &&
                    !locations.contains(location)){
                String href = ad.$(".iva-item-title-py3i_ a").getAttribute("href");
                if (!hrefs.contains(href)){
                    notifications(href, fullTime);
                    hrefs.add(href);

                }
            }else {
                return;
            }
        }
    }

    private static void notifications(String href, String time){
        given()
                .contentType(ContentType.JSON)
                .body("{\"chat_id\": \"" + chatId + "\", \"text\": \""+ time + "\n " + href  +"\", \"disable_notification\": true}")
                .post("https://api.telegram.org/"+ tokenBot +"/sendMessage");
    }
}
