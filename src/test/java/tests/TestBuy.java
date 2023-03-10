package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import step.Steps;

import static com.codeborne.selenide.Selenide.open;

public class TestBuy {

    @BeforeAll
    static void setUp() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }
    @BeforeEach
    void openWeb(){
        open("http://localhost:8080");
    }
    @AfterAll
    static void tearDown (){
        SelenideLogger.removeListener("allure");
    }
    Steps step = new Steps();
@Test
    void test (){
    step.selectBuy();
    step.validDate();
    Configuration.holdBrowserOpen = true;
}
}
