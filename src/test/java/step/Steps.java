package step;

import com.codeborne.selenide.Condition;
import data.CardDataHelper;
import data.PageElement;
import io.qameta.allure.Step;

public class Steps {
    PageElement pageElement = new PageElement();

    //Форма для заполнения полей
    public void fillField(String cardNum, String month, String year, String owner, String cvv) {
        pageElement.getCardNumberField().setValue(cardNum);
        pageElement.getMonthField().setValue(month);
        pageElement.getYearField().setValue(year);
        pageElement.getOwnerField().setValue(owner);
        pageElement.getCvcField().setValue(cvv);
    }

    //Кнопка продолжить
    public void clickContinue() {
        pageElement.getContinueButton().click();
    }

    @Step("Выбор блока купить")
    public void selectBuy() {
        pageElement.getButtonPay().click();
    }

    @Step("Выбор блока купить в кредит")
    public void selectBuyCredit() {
        pageElement.getButtonPayCredit().click();
    }


    @Step("Проверка сообщения о положительном решении банка")
    public void checkSuccessMsg() {
        pageElement.getSuccessOperation().waitUntil(Condition.visible, 15000);
    }


    @Step("Проверка сообщения об отклонении операции банком")
    public void checkAbortMsg() {
        pageElement.getErrorOperation().waitUntil(Condition.visible, 15000);
    }

    @Step("Проверка сообщения об истечении срока действия карты")
    public void checkExpiredCardMsg() {
        pageElement.getErrorMonth().waitUntil(Condition.visible, 15000);
    }


    @Step("Заполнение валидным значениями (карта Approved)")
    public void validDateApprovedCard() {
        String cardNum = CardDataHelper.cardValidApproved();
        String month = CardDataHelper.monthValid();
        String year = CardDataHelper.yearValid();
        String owner = CardDataHelper.ownerValid();
        String cvv = CardDataHelper.cvvValid();
        fillField(cardNum, month, year, owner, cvv);
        clickContinue();
    }

    @Step("Заполнение валидными значениям (Карта Declined)")
    public void validDateDeclinedCard() {
        String cardNum = CardDataHelper.cardValidDeclined();
        String month = CardDataHelper.monthValid();
        String year = CardDataHelper.yearValid();
        String owner = CardDataHelper.ownerValid();
        String cvv = CardDataHelper.cvvValid();
        fillField(cardNum, month, year, owner, cvv);
        clickContinue();
    }

    @Step("Заполнение полей с невалидной картой")
    public void validDateInvalidCard() {
        String cardNum = CardDataHelper.cardInValid();
        String month = CardDataHelper.monthValid();
        String year = CardDataHelper.yearValid();
        String owner = CardDataHelper.ownerValid();
        String cvv = CardDataHelper.cvvValid();
        fillField(cardNum, month, year, owner, cvv);
        clickContinue();
    }

    @Step("Заполнение полей с месяцем равным больше 12")
    public void monthMore12() {
        String cardNum = CardDataHelper.cardValidApproved();
        String month = CardDataHelper.monthMore12();
        String year = CardDataHelper.yearValid();
        String owner = CardDataHelper.ownerValid();
        String cvv = CardDataHelper.cvvValid();
        fillField(cardNum, month, year, owner, cvv);
        clickContinue();
    }
    @Step ("Заполнение полей с месяцем равным 00")
    public void monthZero (){
        String cardNum = CardDataHelper.cardValidApproved();
        String month = CardDataHelper.monthZero();
        String year = CardDataHelper.yearValid();
        String owner = CardDataHelper.ownerValid();
        String cvv = CardDataHelper.cvvValid();
        fillField(cardNum, month, year, owner, cvv);
        clickContinue();
    }
}
