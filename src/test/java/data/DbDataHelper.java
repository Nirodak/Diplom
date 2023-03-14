package data;

import lombok.Data;

@Data
public class DbDataHelper {
    // Статус карты Approved
    private String statusApproved = "APPROVED";
    // Статус карты Declined
    private String statusDeclined = "DECLINED";
}
