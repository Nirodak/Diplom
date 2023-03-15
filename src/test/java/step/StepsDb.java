package step;

import data.DbDataHelper;
import io.qameta.allure.Step;
import lombok.val;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StepsDb {

    // Получение url из properties
    private static String url() {
        return System.getProperty("db.url");
    }

    // Получение username из properties
    private static String username() {
        return System.getProperty("username");
    }

    // Получение password из properties
    private static String password() {
        return System.getProperty("password");
    }

    //Подключение к БД
    public static Connection conn() {
        Connection conn;
        try {
            conn = DriverManager.getConnection(url(), username(), password());
            {
                return conn;
            }
        } catch (Exception exception) {
            System.out.println(exception);
        }
        return null;
    }

    // Очистка БД
    public static void cleanTables() throws SQLException {
        val deletePaymentEntity = "DELETE FROM payment_entity;";
        val deleteCreditEntity = "DELETE FROM credit_request_entity;";
        val deleteOrderEntity = "DELETE FROM order_entity;";
        try (
                Connection connectionMySQL = conn();

                PreparedStatement statementPaymentEntity = connectionMySQL.prepareStatement(deletePaymentEntity);
                PreparedStatement statementCreditEntity = connectionMySQL.prepareStatement(deleteCreditEntity);
                PreparedStatement statementOrderEntity = connectionMySQL.prepareStatement(deleteOrderEntity);
        ) {
            statementPaymentEntity.executeUpdate();
            statementCreditEntity.executeUpdate();
            statementOrderEntity.executeUpdate();
        }
    }

    @Step("Выборка полей БД payment_entity")
    public DbDataHelper paymentEntityValues() throws SQLException {
        val runner = new QueryRunner();
        val selectPaymentValues = "SELECT status, amount, transaction_id FROM payment_entity;";
        return runner.query(conn(), selectPaymentValues, new BeanHandler<>(DbDataHelper.class));
    }

    @Step("Выборка полей БД credit_entity")
    public DbDataHelper creditEntityValues() throws SQLException {
        val runner = new QueryRunner();
        val selectCreditValues = "SELECT status, bank_id FROM credit_request_entity;";
        return runner.query(conn(), selectCreditValues, new BeanHandler<>(DbDataHelper.class));
    }

    @Step("Выборка полей БД order_entity")
    public DbDataHelper orderEntityValues() throws SQLException {
        val runner = new QueryRunner();
        val selectOrderValues = "SELECT credit_id, payment_id FROM order_entity;";
        return runner.query(conn(), selectOrderValues, new BeanHandler<>(DbDataHelper.class));
    }

    @Step("Счётчик полей БД payment_entity")
    public int paymentEntityQuantityCount() throws SQLException {
        val runner = new QueryRunner();
        val selectPaymentQuantity = "SELECT COUNT(*) FROM order_entity;";
        val countPayment = runner.query(conn(), selectPaymentQuantity, new ScalarHandler<>());
        return Integer.parseInt(countPayment.toString());
    }

    @Step("Счётчик полей БД credit_entity")
    public int creditEntityQuantityCount() throws SQLException {
        val runner = new QueryRunner();
        val selectCreditQuantity = "SELECT COUNT(*) FROM credit_request_entity;";
        val countCredit = runner.query(conn(), selectCreditQuantity, new ScalarHandler<>());
        return Integer.parseInt(countCredit.toString());
    }

    @Step("Счётчик полей БД order_entity")
    public int orderEntityQuantityCount() throws SQLException {
        val runner = new QueryRunner();
        val selectOrderQuantity = "SELECT COUNT(*) FROM order_entity;";
        val countOrder = runner.query(conn(), selectOrderQuantity, new ScalarHandler<>());
        return Integer.parseInt(countOrder.toString());
    }

}
