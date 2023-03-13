package tests;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Issue;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import step.Steps;

import static com.codeborne.selenide.Selenide.open;

public class TestCredit {

    @BeforeAll
    static void setUp() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @BeforeEach
    void openBuyWeb() {
        open("http://localhost:8080");
        step.selectCredit();
    }

    @AfterAll
    static void tearDown() {
        SelenideLogger.removeListener("allure");
    }

    Steps step = new Steps();

    @Epic(value = "Проверка дебетовой карты")
    @Feature(value = "Проверка валидных значений")
    @Test
    @DisplayName(value = "1.1.1 Тест валидных значений карта Approved/владелец на латинице")
    void testValidApproved() {
        step.validDateApprovedCard();
        step.checkSuccessMsg();
    }

    @Epic(value = "Проверка дебетовой карты")
    @Feature(value = "Проверка валидных значений")
    @Test
    @Issue(value = "2")
    @DisplayName("1.1.2 Тест валидных значений карта Declined/владелец на латинице")
    void testValidDeclined() {
        step.validDateDeclinedCard();
        step.checkAbortMsg();
    }

    @Epic(value = "Проверка дебетовой карты")
    @Feature(value = "Проверка валидных значений")
    @Test
    @DisplayName("1.1.3. Тест валидных значений карта Approved/владелец на кирилиице ")
    void testOwnerKirillica() {
        step.ownerKirillica();
        step.checkSuccessMsg();

    }

    @Epic(value = "Проверка дебетовой карты")
    @Feature(value = "Проверка невалидного номера карты")
    @Test
    @DisplayName("1.2.1 Тест невалидная карта (карта отсутствует в БД)")
    void testInvalidCard() {
        step.validDateInvalidCard();
        step.checkAbortMsg();
    }

    @Epic(value = "Проверка дебетовой карты")
    @Feature(value = "Проверка невалидного месяца")
    @Test
    @DisplayName("1.3.1 Тест месяц больше 12")
    void testMonthMore12() {
        step.monthMore12();
        step.checkInvalidCardMsg();
    }

    @Epic(value = "Проверка дебетовой карты")
    @Feature(value = "Проверка невалидного месяца")
    @Test
    @Issue(value = "3")
    @DisplayName("1.3.2 Тест месяц равен 00")
    void testMonthZero() {
        step.monthZero();
        step.checkInvalidCardMsg();
    }

    @Epic(value = "Проверка дебетовой карты")
    @Feature(value = "Проверка невалидного месяца")
    @Test
    @Issue(value = "")
    @DisplayName("1.3.3 Тест месяц действия карты просрочен")
    void testMonthOverdue() {
        step.monthOverdue();
        step.checkExpiredCardMsg();
    }

    @Epic(value = "Проверка дебетовой карты")
    @Feature(value = "Проверка невалидого года")
    @Test
    @DisplayName("1.4.1 Тест год действия карты просрочен")
    void testYearOverdue() {
        step.yearOverdue();
        step.checkExpiredCardMsg();
    }

    @Epic(value = "Проверка дебетовой карты")
    @Feature(value = "Проверка невалидого года")
    @Test
    @DisplayName("1.4.2 Тест год действия карты превышает 6 лет")
    void testYearExceeded() {
        step.yearExceeded();
        step.checkInvalidCardMsg();
    }

    @Epic(value = "Проверка дебетовой карты")
    @Feature(value = "Проверка невалидного владельца")
    @Test
    @Issue(value = "")
    @DisplayName("1.5.1 Тест владельца со спец. символами")
    void testOwnerSpecSymbol() {
        step.ownerSpecSymbol();
        step.checkInvalidOwner();
    }

    @Epic(value = "Проверка дебетовой карты")
    @Feature(value = "Проверка невалидного CVV")
    @Test
    @DisplayName("1.6.1 Тест CVV равного 00")
    void testCvvZero() {
        step.cvvZero();
        step.checkFormatDate();
    }

    @Epic(value = "Проверка дебетовой карты")
    @Feature(value = "Проверка заполнения полей меньше требуемого количества символов")
    @Test
    @DisplayName("1.7.1 Тест длинна номера карты меньше 16")
    void testCardCharLessRequired() {
        step.cardCharLessRequired();
        step.checkFormatDate();
    }

    @Epic(value = "Проверка дебетовой карты")
    @Feature(value = "Проверка заполнения полей меньше требуемого количества символов")
    @Test
    @DisplayName("1.7.2 Тест значение месяца меньше 2 символов")
    void testMonthCharLessRequired() {
        step.monthCharLessRequired();
        step.checkFormatDate();
    }

    @Epic(value = "Проверка дебетовой карты")
    @Feature(value = "Проверка заполнения полей меньше требуемого количества символов")
    @Test
    @DisplayName("1.7.3 Тест значение года меньше 2 символов")
    void testYearCharLessRequired() {
        step.yearCharLessRequired();
        step.checkFormatDate();
    }

    @Epic(value = "Проверка дебетовой карты")
    @Feature(value = "Проверка заполнения полей меньше требуемого количества символов")
    @Test
    @DisplayName("1.7.4 Тест значение cvv меньше 3 символов")
    void testCvvCardCharLessRequired() {
        step.cvvCharLessRequired();
        step.checkFormatDate();
    }

    @Epic(value = "Проверка дебетовой карты")
    @Feature(value = "Проверка заполнения формы с пустыми полями")
    @Test
    @DisplayName("1.8.1 Тест пустое поле номера карты")
    void testCardNull() {
        step.cardNull();
        step.checkFormatDate();
    }

    @Epic(value = "Проверка дебетовой карты")
    @Feature(value = "Проверка заполнения формы с пустыми полями")
    @Test
    @DisplayName("1.8.2 Тест пустое поле месяц")
    void testMonthNull() {
        step.monthNull();
        step.checkFormatDate();
    }

    @Epic(value = "Проверка дебетовой карты")
    @Feature(value = "Проверка заполнения формы с пустыми полями")
    @Test
    @DisplayName("1.8.3 Тест пустое поле года")
    void testYearNull() {
        step.yearNull();
        step.checkFormatDate();
    }

    @Epic(value = "Проверка дебетовой карты")
    @Feature(value = "Проверка заполнения формы с пустыми полями")
    @Test
    @DisplayName("1.8.4 Тест пустое поле владелец")
    void testOwnerNull() {
        step.ownerNull();
        step.checkFormatDate();
    }

    @Epic(value = "Проверка дебетовой карты")
    @Feature(value = "Проверка заполнения формы с пустыми полями")
    @Test
    @DisplayName("1.8.5 Тест пустое поле CVV")
    void testCvvNull() {
        step.cvvNull();
        step.checkFormatDate();

    }
}