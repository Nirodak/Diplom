package tests;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Issue;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import step.StepsDb;
import step.StepsSelenide;

import java.sql.SQLException;

import static com.codeborne.selenide.Selenide.open;

public class TestCredit {
    StepsSelenide stepSelenide = new StepsSelenide();


    @BeforeAll
    static void setUp() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @BeforeEach
    void openBuyWeb() {
        open(System.getProperty("webservice.url"));
        stepSelenide.selectCredit();

    }

    @AfterEach
    void cleanDB() throws SQLException {
        StepsDb.cleanTables();
    }

    @AfterAll
    static void tearDown() {
        SelenideLogger.removeListener("allure");
    }

    @Epic(value = "Проверка кредитной оплаты")
    @Feature(value = "Проверка валидных значений")
    @Test
    @DisplayName(value = "2.1.1 Тест валидных значений карта Approved")
    void testValidApproved() {
        stepSelenide.validDateApprovedCard();
        stepSelenide.checkSuccessMsg();
    }

    @Epic(value = "Проверка кредитной карты")
    @Feature(value = "Проверка валидных значений")
    @Test
    @Issue(value = "2")
    @DisplayName("2.1.2 Тест валидных значений карта Declined")
    void testValidDeclined() {
        stepSelenide.validDateDeclinedCard();
        stepSelenide.checkAbortMsg();
    }

    @Epic(value = "Проверка кредитной карты")
    @Feature(value = "Проверка не валидного номера карты")
    @Test
    @DisplayName("2.2.1 Тест не валидная карта (карта отсутствует в БД)")
    void testInvalidCard() {
        stepSelenide.cardDateInvalid();
        stepSelenide.checkAbortMsg();
    }

    @Epic(value = "Проверка кредитной карты")
    @Feature(value = "Проверка не валидного номера карты")
    @Test
    @DisplayName("2.2.2 Тест длинна номера карты меньше 16")
    void testCardCharLessRequired() {
        stepSelenide.cardCharLessRequired();
        stepSelenide.checkFormatDate();
    }

    @Epic(value = "Проверка кредитной карты")
    @Feature(value = "Проверка не валидного месяца")
    @Test
    @DisplayName("2.3.1 Тест месяц больше 12")
    void testMonthMore12() {
        stepSelenide.monthMore12();
        stepSelenide.checkInvalidCardMsg();
    }

    @Epic(value = "Проверка кредитной карты")
    @Feature(value = "Проверка не валидного месяца")
    @Test
    @Issue(value = "3")
    @DisplayName("2.3.2 Тест месяц равен 00")
    void testMonthZero() {
        stepSelenide.monthZero();
        stepSelenide.checkInvalidCardMsg();
    }

    @Epic(value = "Проверка кредитной карты")
    @Feature(value = "Проверка не валидного месяца")
    @Test
    @Issue(value = "")
    @DisplayName("2.3.3 Тест месяц действия карты просрочен")
    void testMonthOverdue() {
        stepSelenide.monthOverdue();
        stepSelenide.checkExpiredCardMsg();
    }

    @Epic(value = "Проверка кредитной карты")
    @Feature(value = "Проверка не валидного месяца")
    @Test
    @DisplayName("2.3.4 Тест значение месяца меньше 2 символов")
    void testMonthCharLessRequired() {
        stepSelenide.monthCharLessRequired();
        stepSelenide.checkFormatDate();
    }

    @Epic(value = "Проверка кредитной карты")
    @Feature(value = "Проверка не валидого года")
    @Test
    @DisplayName("2.4.1 Тест год действия карты просрочен")
    void testYearOverdue() {
        stepSelenide.yearOverdue();
        stepSelenide.checkExpiredCardMsg();
    }

    @Epic(value = "Проверка кредитной карты")
    @Feature(value = "Проверка не валидого года")
    @Test
    @DisplayName("2.4.2 Тест год действия карты превышает 6 лет")
    void testYearExceeded() {
        stepSelenide.yearExceeded();
        stepSelenide.checkInvalidCardMsg();
    }

    @Epic(value = "Проверка кредитной карты")
    @Feature(value = "Проверка не валидого года")
    @Test
    @DisplayName("2.4.3 Тест значение года меньше 2 символов")
    void testYearCharLessRequired() {
        stepSelenide.yearCharLessRequired();
        stepSelenide.checkFormatDate();
    }

    @Epic(value = "Проверка кредитной карты")
    @Feature(value = "Проверка не валидного владельца")
    @Test
    @Issue(value = "4")
    @DisplayName("2.5.1 Тест владельца со спец. символами")
    void testOwnerSpecSymbol() {
        stepSelenide.ownerSpecSymbol();
        stepSelenide.checkInvalidOwner();
    }

    @Epic(value = "Проверка кредитной карты")
    @Feature(value = "Проверка не валидного владельца")
    @Test
    @DisplayName("2.5.2 Тест владельца на кириллице")
    void testOwnerKirillica() {
        stepSelenide.ownerKirillica();
        stepSelenide.checkFormatDate();

    }

    @Epic(value = "Проверка кредитной карты")
    @Feature(value = "Проверка не валидного CVV")
    @Test
    @DisplayName("2.6.1 Тест CVV равного 00")
    void testCvvZero() {
        stepSelenide.cvvZero();
        stepSelenide.checkFormatDate();
    }

    @Epic(value = "Проверка кредитной карты")
    @Feature(value = "Проверка не валидного CVV")
    @Test
    @DisplayName("2.6.2 Тест значение cvv меньше 3 символов")
    void testCvvCardCharLessRequired() {
        stepSelenide.cvvCharLessRequired();
        stepSelenide.checkFormatDate();
    }


    @Epic(value = "Проверка кредитной карты")
    @Feature(value = "Проверка заполнения формы с пустыми полями")
    @Test
    @DisplayName("2.8.1 Тест пустое поле номера карты")
    void testCardNull() {
        stepSelenide.cardNull();
        stepSelenide.checkFormatDate();
    }

    @Epic(value = "Проверка кредитной карты")
    @Feature(value = "Проверка заполнения формы с пустыми полями")
    @Test
    @DisplayName("2.8.2 Тест пустое поле месяц")
    void testMonthNull() {
        stepSelenide.monthNull();
        stepSelenide.checkFormatDate();
    }

    @Epic(value = "Проверка кредитной карты")
    @Feature(value = "Проверка заполнения формы с пустыми полями")
    @Test
    @DisplayName("2.8.3 Тест пустое поле года")
    void testYearNull() {
        stepSelenide.yearNull();
        stepSelenide.checkFormatDate();
    }

    @Epic(value = "Проверка кредитной карты")
    @Feature(value = "Проверка заполнения формы с пустыми полями")
    @Test
    @DisplayName("2.8.4 Тест пустое поле владелец")
    void testOwnerNull() {
        stepSelenide.ownerNull();
        stepSelenide.checkFormatDate();
    }

    @Epic(value = "Проверка кредитной карты")
    @Feature(value = "Проверка заполнения формы с пустыми полями")
    @Test
    @DisplayName("2.8.5 Тест пустое поле CVV")
    void testCvvNull() {
        stepSelenide.cvvNull();
        stepSelenide.checkFormatDate();
    }


}
