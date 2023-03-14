package step;

import data.CardDataHelper;
import data.PageElement;
import io.qameta.allure.Step;

public class StepsDB {

    PageElement pageElement = new PageElement();
    public void fillField(String cardNum, String month, String year, String owner, String cvv) {
        pageElement.getCardNumberField().setValue(cardNum);
        pageElement.getMonthField().setValue(month);
        pageElement.getYearField().setValue(year);
        pageElement.getOwnerField().setValue(owner);
        pageElement.getCvcField().setValue(cvv);
    }

    //Кнопка продолжить
    private void clickContinue() {
        pageElement.getContinueButton().click();
    }
    @Step("Заполнение валидным значениями (карта Approved)")
    public void cardApprovedDB() {
        String cardNum = CardDataHelper.cardValidApproved();
        String month = CardDataHelper.monthPlusOne();
        String year = CardDataHelper.yearPlusOne();
        String owner = CardDataHelper.ownerValid();
        String cvv = CardDataHelper.cvvValid();
        fillField(cardNum, month, year, owner, cvv);
        clickContinue();
    }

    @Step("Заполнение валидными значениям (Карта Declined)")
    public void cardDeclinedDB() {
        String cardNum = CardDataHelper.cardValidDeclined();
        String month = CardDataHelper.monthPlusOne();
        String year = CardDataHelper.yearPlusOne();
        String owner = CardDataHelper.ownerValid();
        String cvv = CardDataHelper.cvvValid();
        fillField(cardNum, month, year, owner, cvv);
        clickContinue();
    }
}
