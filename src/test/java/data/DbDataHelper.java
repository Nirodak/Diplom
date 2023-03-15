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
    private String transaction_id;
    //Id банка из БД
    private String bank_id;
    //Id оплаты дебетовой карты
    private String payment_id;
    //Id оплаты кредитной карты
    private String credit_id;
    //стоимость тура по условию
    private int costTour = 4500000;
}
