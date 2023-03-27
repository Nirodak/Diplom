package tests;

import com.codeborne.selenide.logevents.SelenideLogger;
import data.DbDataHelper;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Issue;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.val;
import org.junit.jupiter.api.*;
import step.StepsDb;
import step.StepsSelenide;

import java.sql.SQLException;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class TestDbBuy {

    DbDataHelper dbDataHelper = new DbDataHelper();
    StepsSelenide stepsSelenide = new StepsSelenide();
    StepsDb stepsDB = new StepsDb();

    @BeforeAll
    static void setUp() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @BeforeEach
    void openBuyWeb() {
        open(System.getProperty("webservice.url"));
        stepsSelenide.selectBuy();
    }

    @AfterEach
    void cleanDB() throws SQLException {
        StepsDb.cleanTables();
    }

    @AfterAll
    static void tearDown() {
        SelenideLogger.removeListener("allure");
    }

    @Epic(value = "Проверка оплаты картой БД")
    @Feature(value = "Сравнение вводимых значений и тестовых данных с записями в БД")
    @Test
    @DisplayName("3.1.1 Сравнение статуса карты Approved со статусом в БД")
    void testCardStatusApproved() throws SQLException {
        stepsSelenide.validDateApprovedCard();
        stepsSelenide.checkNotificationStatusOperation();
        val expected = dbDataHelper.getStatusApproved();
        val actual = stepsDB.paymentEntityValues().getStatus();
        assertEquals(expected, actual);
    }

    @Epic(value = "Проверка оплаты картой БД")
    @Feature(value = "Сравнение вводимых значений и тестовых данных с записями в БД")
    @Test
    @DisplayName("3.1.2 Сравнение статуса карты Declined со статусом в БД")
    void testCardStatusDeclined() throws SQLException {
        stepsSelenide.validDateDeclinedCard();
        stepsSelenide.checkNotificationStatusOperation();
        val expected = dbDataHelper.getStatusDeclined();
        val actual = stepsDB.paymentEntityValues().getStatus();
        assertEquals(expected, actual);
    }

    @Epic(value = "Проверка оплаты картой БД")
    @Feature(value = "Сравнение вводимых значений и тестовых данных с записями в БД")
    @Test
    @DisplayName("3.1.3 Сравнение поля transaction_id в payment_entity с полем payment_id в order_entity")
    void checkTransactionIdWithPaymentId() throws SQLException {
        stepsSelenide.validDateApprovedCard();
        stepsSelenide.checkNotificationStatusOperation();
        val expected = stepsDB.paymentEntityValues().getTransaction_id();
        val actual = stepsDB.orderEntityValues().getPayment_id();
        assertEquals(expected, actual);
    }

    @Epic(value = "Проверка оплаты картой БД")
    @Feature(value = "Сравнение вводимых значений и тестовых данных с записями в БД")
    @Test
    @Issue(value = "7")
    @DisplayName("3.1.4 Сравнение стоимости тура со значением amount в payment_entity")
    void checkCostWithAmount() throws SQLException {
        stepsSelenide.validDateApprovedCard();
        stepsSelenide.checkNotificationStatusOperation();
        val expected = dbDataHelper.getCostTour();
        val actual = stepsDB.paymentEntityValues().getAmount();
        assertEquals(expected, actual);
    }

    @Epic(value = "Проверка оплаты картой БД")
    @Feature(value = "Сравнение вводимых значений и тестовых данных с записями в БД")
    @Test
    @DisplayName("3.1.5 Проверка отсутствия записей в БД в случае отклонения операции")
    void checkAbortOperationPaymentEntity() throws SQLException {
        val expectedPayment = stepsDB.paymentEntityQuantityCount();
        val expectedOrder = stepsDB.orderEntityQuantityCount();
        stepsSelenide.cardDateInvalid();
        stepsSelenide.checkNotificationStatusOperation();
        val actualPayment = stepsDB.paymentEntityQuantityCount();
        val actualOrder = stepsDB.orderEntityQuantityCount();
        assertEquals(expectedPayment, actualPayment);
        assertEquals(expectedOrder, actualOrder);
    }
}
