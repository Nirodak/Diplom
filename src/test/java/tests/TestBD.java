package tests;

import com.codeborne.selenide.logevents.SelenideLogger;
import data.CardDataHelper;
import data.DbDataHelper;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.val;
import org.junit.jupiter.api.*;
import step.StepsDB;
import step.StepsSelenide;

import java.sql.SQLException;

import static com.codeborne.selenide.Selenide.open;


public class TestBD {
    DbDataHelper dbDataHelper = new DbDataHelper();
    StepsSelenide stepsSelenide = new StepsSelenide();

    @BeforeAll
    static void setUp() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @BeforeEach
    void openBuyWeb() {
        open("http://localhost:8080");
        stepsSelenide.selectBuy();
    }

    @AfterEach
    void cleanDB() throws SQLException {
        StepsDB.cleanTables();
    }

    @AfterAll
    static void tearDown() {
        SelenideLogger.removeListener("allure");
    }

    @Test
    @DisplayName("проверка статуса у карта Approved")
    public void testCardStatusApproved (){
        stepsSelenide.validDateApprovedCard();
        val expected = dbDataHelper.getStatusApproved();
        val actual =
    }
}
