package tests;

import com.codeborne.selenide.logevents.SelenideLogger;
import data.DbDataHelper;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.val;
import org.junit.jupiter.api.*;
import step.StepsDb;
import step.StepsSelenide;

import java.sql.SQLException;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestDbCredit {  DbDataHelper dbDataHelper = new DbDataHelper();
    StepsSelenide stepsSelenide = new StepsSelenide();
    StepsDb stepsDB = new StepsDb();

    @BeforeAll
    static void setUp() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @BeforeEach
    void openBuyWeb() {
        open("http://localhost:8080");
        stepsSelenide.selectCredit();
    }

    @AfterEach
    void cleanDB() throws SQLException {
        StepsDb.cleanTables();
    }

    @AfterAll
    static void tearDown() {
        SelenideLogger.removeListener("allure");
    }

    @Epic(value = "Проверка кредитной оплаты БД")
    @Feature(value = "Сравнение вводимых значений и тестовых данных с записями в БД")
    @Test
    @DisplayName("4.1.1 Сравнение статуса карты Approved со статусом в БД")
    void testCardStatusApproved() throws SQLException {
        stepsSelenide.validDateApprovedCard();
        stepsSelenide.checkNotificationStatusOperation();
        val expected = dbDataHelper.getStatusApproved();
        val actual = stepsDB.creditEntityValues().getStatus();
        assertEquals(expected, actual);
    }

    @Epic(value = "Проверка кредитной оплаты БД")
    @Feature(value = "Сравнение вводимых значений и тестовых данных с записями в БД")
    @Test
    @DisplayName("4.1.2 Сравнение статуса карты Declined со статусом в БД")
    void testCardStatusDeclined() throws SQLException {
        stepsSelenide.validDateDeclinedCard();
        stepsSelenide.checkNotificationStatusOperation();
        val expected = dbDataHelper.getStatusDeclined();
        val actual = stepsDB.creditEntityValues().getStatus();
        assertEquals(expected, actual);
    }

    @Epic(value = "Проверка кредитной оплаты БД")
    @Feature(value = "Сравнение вводимых значений и тестовых данных с записями в БД")
    @Test
    @DisplayName("4.1.3 Сравнение поля bank_id в credit_entity с полем credit_id в order_entity")
    void checkTransactionIdWithCreditId() throws SQLException {
        stepsSelenide.validDateApprovedCard();
        stepsSelenide.checkNotificationStatusOperation();
        val expected = stepsDB.creditEntityValues().getBank_id();
        val actual = stepsDB.orderEntityValues().getCredit_id();
        assertEquals(expected, actual);
    }

    @Epic(value = "Проверка кредитной оплаты БД")
    @Feature(value = "Сравнение вводимых значений и тестовых данных с записями в БД")
    @Test
    @DisplayName("4.1.4 Проверка отсутствия записей в БД в случае отклонения операции")
    void checkAbortOperationPaymentEntity() throws SQLException {
        val expectedCredit = stepsDB.creditEntityQuantityCount();
        val expectedOrder = stepsDB.orderEntityQuantityCount();
        stepsSelenide.cardDateInvalid();
        stepsSelenide.checkNotificationStatusOperation();
        val actualCredit = stepsDB.creditEntityQuantityCount();
        val actualOrder = stepsDB.orderEntityQuantityCount();
        assertEquals(expectedCredit, actualCredit);
        assertEquals(expectedOrder, actualOrder);
    }
}
