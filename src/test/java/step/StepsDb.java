package step;

import data.DbDataHelper;
import io.qameta.allure.Step;
import lombok.val;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

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
    @Step ("Выборка полей БД payment_entity")
    public DbDataHelper DbPaymentEntityValues () throws SQLException {
        val selectPaymentValues = "SELECT status, amount, transaction_id FROM payment_entity;";
        val runner = new QueryRunner();
        return runner.query(conn(), selectPaymentValues, new BeanHandler<>(DbDataHelper.class));
    }

    @Step("Выборка полей БД credit_entity")
    public void DbCreditEntityValues(){

    }
    @Step ("Выборка полей БД order_entity")
    public void DbOrderEntityValues(){

    }

}
