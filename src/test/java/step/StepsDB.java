package step;

import io.qameta.allure.Step;
import lombok.val;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StepsDB {
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
        val deleteOrderEntity = "DELETE FROM order_entity;";
        val deletePaymentEntity = "DELETE FROM payment_entity;";
        val deleteCreditEntity = "DELETE FROM credit_request_entity;";
        try (
                Connection connectionMySQL = conn();

                PreparedStatement statementOrderEntity = connectionMySQL.prepareStatement(deleteOrderEntity);
                PreparedStatement statementPaymentEntity = connectionMySQL.prepareStatement(deletePaymentEntity);
                PreparedStatement statementCreditEntity = connectionMySQL.prepareStatement(deleteCreditEntity);
        ) {
            statementOrderEntity.executeUpdate();
            statementPaymentEntity.executeUpdate();
            statementCreditEntity.executeUpdate();
        }
    }
    @Step ("Проверка поля статус")
    public void

}
