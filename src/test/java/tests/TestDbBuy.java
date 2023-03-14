package tests;

import com.codeborne.selenide.logevents.SelenideLogger;
import data.DbDataHelper;
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
        open("http://localhost:8080");
        stepsSelenide.selectBuy();
    }

//    @AfterEach
//    void cleanDB() throws SQLException {
//        StepsDb.cleanTables();
//    }

    @AfterAll
    static void tearDown() {
        SelenideLogger.removeListener("allure");
    }

    @Test
    @DisplayName("проверка статуса у карта Approved")
    public void testCardStatusApproved () throws SQLException {
        stepsSelenide.validDateApprovedCard();
        stepsSelenide.checkNotificationStatusOperation();
        val expected = dbDataHelper.getStatusApproved();
        val actual = stepsDB.DbPaymentEntityValues().getStatus();
        assertEquals(expected,actual);
    }
    @Test
    @DisplayName("проверка статуса у карты Declined")
    public void testCardStatusDeclined () throws SQLException {
        stepsSelenide.validDateDeclinedCard();
        stepsSelenide.checkNotificationStatusOperation();
        val expected = dbDataHelper.getStatusDeclined();
        val actual = stepsDB.DbPaymentEntityValues().getStatus();
        assertEquals(expected, actual);
    }
}
