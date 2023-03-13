package tests;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Issue;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import step.Steps;

import static com.codeborne.selenide.Selenide.open;

public class TestBuy {

    @BeforeAll
    static void setUp() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @BeforeEach
    void openWeb() {
        open("http://localhost:8080");
    }

    @AfterAll
    static void tearDown() {
        SelenideLogger.removeListener("allure");
    }

    Steps step = new Steps();

    @Epic(value = "Проверка дебетовой карты")
    @Feature(value = "Проверка валидных карт")
    @Test
    @DisplayName(value = "Тест валидных значений карта Approved")
    void testValidApproved() {
        step.selectBuy();
        step.validDateApprovedCard();
        step.checkSuccessMsg();
    }

    @Epic(value = "Проверка дебетовой карты")
    @Feature(value = "Проверка валидных карт")
    @Test
    @Issue(value = "1")
    @DisplayName("Тест валидных значений карта Declined")
    void testValidDeclined() {
        step.selectBuy();
        step.validDateDeclinedCard();
        step.checkAbortMsg();
    }

    @Epic(value = "Проверка дебетовой карты")
    @Feature(value = "Невалидная карта")
    @Test
    @DisplayName("Невалидная карта")
    void testInvalidCard() {
        step.selectBuy();
        step.validDateInvalidCard();
        step.checkAbortMsg();
    }

    @Epic(value = "Проверка дебетовой карты")
    @Feature(value = "Проверка невалидных месяцов")
    @Test
    @DisplayName("Месяц больше 12")
    void monthMore12() {
        step.selectBuy();
        step.monthMore12();
        step.checkExpiredCardMsg();
    }

    @Epic(value = "Проверка дебетовой карты")
    @Feature(value = "Проверка невалидных месяцов")
    @Test
    @DisplayName("Месяц равен 00")
    void monthZero() {
        step.selectBuy();
        step.monthZero();
        step.checkExpiredCardMsg();
    }


}
