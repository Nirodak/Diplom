package data;

import lombok.Data;

@Data
public class DbDataHelper {
    // Статус карты Approved
    private String statusApproved = "APPROVED";
    // Статус карты Declined
    private String statusDeclined = "DECLINED";
    // Статус из БД
    private String status;
    // сумма из БД
    private int amount;
    //Id транзакции из БД
    private int transactionId;
    //Id банка из БД
    private int bankId;
    //Id оплаты дебетовой карты
    private int paymentID;
    //Id оплаты кредитной карты
    private int creditID;

}
