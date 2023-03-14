package step;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import data.CardDataHelper;
import data.PageElement;
import io.qameta.allure.Step;

public class StepsSelenide {
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
    private void clickContinue() {
        pageElement.getContinueButton().click();
    }

    private void checkTimeout(SelenideElement element) {
        element.waitUntil(Condition.visible, 20000);
    }


    @Step("Выбор блока купить")
    public void selectBuy() {
        pageElement.getButtonPay().click();
    }

    @Step("Выбор блока купить в кредит")
    public void selectCredit() {
        pageElement.getButtonPayCredit().click();
    }


    @Step("Проверка сообщения о положительном решении банка")
    public void checkSuccessMsg() {
        checkTimeout(pageElement.getSuccessOperation());
    }


    @Step("Проверка сообщения об отклонении операции банком")
    public void checkAbortMsg() {
        checkTimeout(pageElement.getErrorOperation());
    }

    @Step("Проверка сообщения об истечении срока действия карты")
    public void checkExpiredCardMsg() {
        pageElement.getErrorExpiredCard().forEach(element -> {
            checkTimeout(element);
        });
    }

    @Step("Проверка сообщения о неверно указанной дате")
    public void checkInvalidCardMsg() {
        checkTimeout(pageElement.getErrorInvalidDateCard());
    }

    @Step("Проверка сообщения о неверно указаном владельце")
    public void checkInvalidOwner() {
        checkTimeout(pageElement.getErrorOwner());
    }

    @Step("Проверка неверного формата данных")
    public void checkFormatDate() {
        pageElement.getErrorFormatText().forEach(element -> {
            checkTimeout(element);
        });
    }


    @Step("Заполнение валидным значениями (карта Approved)")
    public void validDateApprovedCard() {
        String cardNum = CardDataHelper.cardValidApproved();
        String month = CardDataHelper.monthPlusOne();
        String year = CardDataHelper.yearPlusOne();
        String owner = CardDataHelper.ownerValid();
        String cvv = CardDataHelper.cvvValid();
        fillField(cardNum, month, year, owner, cvv);
        clickContinue();
    }

    @Step("Заполнение валидными значениям (Карта Declined)")
    public void validDateDeclinedCard() {
        String cardNum = CardDataHelper.cardValidDeclined();
        String month = CardDataHelper.monthPlusOne();
        String year = CardDataHelper.yearPlusOne();
        String owner = CardDataHelper.ownerValid();
        String cvv = CardDataHelper.cvvValid();
        fillField(cardNum, month, year, owner, cvv);
        clickContinue();
    }

    @Step("Заполнение полей с не валидной картой")
    public void validDateInvalidCard() {
        String cardNum = CardDataHelper.cardInValid();
        String month = CardDataHelper.monthPlusOne();
        String year = CardDataHelper.yearPlusOne();
        String owner = CardDataHelper.ownerValid();
        String cvv = CardDataHelper.cvvValid();
        fillField(cardNum, month, year, owner, cvv);
        clickContinue();
    }

    @Step("Заполнение полей с месяцем равным больше 12")
    public void monthMore12() {
        String cardNum = CardDataHelper.cardValidApproved();
        String month = CardDataHelper.monthMore12();
        String year = CardDataHelper.yearPlusOne();
        String owner = CardDataHelper.ownerValid();
        String cvv = CardDataHelper.cvvValid();
        fillField(cardNum, month, year, owner, cvv);
        clickContinue();
    }

    @Step("Заполнение полей с просроченной картой в текущем году")
    public void monthOverdue() {
        String cardNum = CardDataHelper.cardValidApproved();
        String month = CardDataHelper.monthMinusOne();
        String year = CardDataHelper.yearCurrent();
        String owner = CardDataHelper.ownerValid();
        String cvv = CardDataHelper.cvvValid();
        fillField(cardNum, month, year, owner, cvv);
        clickContinue();
    }

    @Step("Заполнение полей с месяцем равным 00")
    public void monthZero() {
        String cardNum = CardDataHelper.cardValidApproved();
        String month = CardDataHelper.monthZero();
        String year = CardDataHelper.yearPlusOne();
        String owner = CardDataHelper.ownerValid();
        String cvv = CardDataHelper.cvvValid();
        fillField(cardNum, month, year, owner, cvv);
        clickContinue();
    }

    @Step("Заполнение полей с годом меньше текущего")
    public void yearOverdue() {
        String cardNum = CardDataHelper.cardValidApproved();
        String month = CardDataHelper.monthPlusOne();
        String year = CardDataHelper.yearOverdue();
        String owner = CardDataHelper.ownerValid();
        String cvv = CardDataHelper.cvvValid();
        fillField(cardNum, month, year, owner, cvv);
        clickContinue();
    }

    @Step("Заполнение полей с годом больше 6 от текущего")
    public void yearExceeded() {
        String cardNum = CardDataHelper.cardValidApproved();
        String month = CardDataHelper.monthPlusOne();
        String year = CardDataHelper.yearExceeded();
        String owner = CardDataHelper.ownerValid();
        String cvv = CardDataHelper.cvvValid();
        fillField(cardNum, month, year, owner, cvv);
        clickContinue();
    }

    @Step("Заполнение полей с владельцем на кириллице")
    public void ownerKirillica() {
        String cardNum = CardDataHelper.cardValidApproved();
        String month = CardDataHelper.monthPlusOne();
        String year = CardDataHelper.yearPlusOne();
        String owner = CardDataHelper.ownerKirillica();
        String cvv = CardDataHelper.cvvValid();
        fillField(cardNum, month, year, owner, cvv);
        clickContinue();
    }

    @Step("Заполнение полей с владельцем имеющим спец.символы")
    public void ownerSpecSymbol() {
        String cardNum = CardDataHelper.cardValidApproved();
        String month = CardDataHelper.monthPlusOne();
        String year = CardDataHelper.yearPlusOne();
        String owner = CardDataHelper.ownerSpecSymbol();
        String cvv = CardDataHelper.cvvValid();
        fillField(cardNum, month, year, owner, cvv);
        clickContinue();
    }

    @Step("Заполнение полей с cvv равным 000")
    public void cvvZero() {
        String cardNum = CardDataHelper.cardValidApproved();
        String month = CardDataHelper.monthPlusOne();
        String year = CardDataHelper.yearPlusOne();
        String owner = CardDataHelper.ownerValid();
        String cvv = CardDataHelper.cvvZero();
        fillField(cardNum, month, year, owner, cvv);
        clickContinue();
    }

    @Step("Заполнение полей с длинной номера карты меньше 16 символов")
    public void cardCharLessRequired() {
        String cardNum = CardDataHelper.cardCharLessRequired();
        String month = CardDataHelper.monthPlusOne();
        String year = CardDataHelper.yearPlusOne();
        String owner = CardDataHelper.ownerValid();
        String cvv = CardDataHelper.cvvValid();
        fillField(cardNum, month, year, owner, cvv);
        clickContinue();
    }

    @Step("Заполнение полей с месяцем меньше 2 символов")
    public void monthCharLessRequired() {
        String cardNum = CardDataHelper.cardValidApproved();
        String month = CardDataHelper.monthCharLessRequired();
        String year = CardDataHelper.yearPlusOne();
        String owner = CardDataHelper.ownerValid();
        String cvv = CardDataHelper.cvvValid();
        fillField(cardNum, month, year, owner, cvv);
        clickContinue();
    }

    @Step("Заполнение полей с годом меньше 2 символов")
    public void yearCharLessRequired() {
        String cardNum = CardDataHelper.cardValidApproved();
        String month = CardDataHelper.monthPlusOne();
        String year = CardDataHelper.yearCharLessRequired();
        String owner = CardDataHelper.ownerValid();
        String cvv = CardDataHelper.cvvValid();
        fillField(cardNum, month, year, owner, cvv);
        clickContinue();
    }

    @Step("Заполнение полей с cvv меньше 3 символов")
    public void cvvCharLessRequired() {
        String cardNum = CardDataHelper.cardValidApproved();
        String month = CardDataHelper.monthPlusOne();
        String year = CardDataHelper.yearPlusOne();
        String owner = CardDataHelper.ownerValid();
        String cvv = CardDataHelper.cvvCharLessRequired();
        fillField(cardNum, month, year, owner, cvv);
        clickContinue();
    }

    @Step("Заполнение полей с пустым номером карты")
    public void cardNull() {
        String cardNum = CardDataHelper.empty();
        String month = CardDataHelper.monthPlusOne();
        String year = CardDataHelper.yearPlusOne();
        String owner = CardDataHelper.ownerValid();
        String cvv = CardDataHelper.cvvValid();
        fillField(cardNum, month, year, owner, cvv);
        clickContinue();
    }

    @Step("Заполнение полей с пустым месяцем")
    public void monthNull() {
        String cardNum = CardDataHelper.cardValidApproved();
        String month = CardDataHelper.empty();
        String year = CardDataHelper.yearPlusOne();
        String owner = CardDataHelper.ownerValid();
        String cvv = CardDataHelper.cvvValid();
        fillField(cardNum, month, year, owner, cvv);
        clickContinue();
    }

    @Step("Заполнение полей с пустым годом")
    public void yearNull() {
        String cardNum = CardDataHelper.cardValidApproved();
        String month = CardDataHelper.monthPlusOne();
        String year = CardDataHelper.empty();
        String owner = CardDataHelper.ownerValid();
        String cvv = CardDataHelper.cvvValid();
        fillField(cardNum, month, year, owner, cvv);
        clickContinue();
    }

    @Step("Заполнение полей с пустым владельцем")
    public void ownerNull() {
        String cardNum = CardDataHelper.cardValidApproved();
        String month = CardDataHelper.monthPlusOne();
        String year = CardDataHelper.yearPlusOne();
        String owner = CardDataHelper.empty();
        String cvv = CardDataHelper.cvvValid();
        fillField(cardNum, month, year, owner, cvv);
        clickContinue();
    }
    @Step ("Заполнение полей с пустым CVV")
    public void cvvNull(){
        String cardNum = CardDataHelper.cardValidApproved();
        String month = CardDataHelper.monthPlusOne();
        String year = CardDataHelper.yearPlusOne();
        String owner = CardDataHelper.ownerValid();
        String cvv = CardDataHelper.empty();
        fillField(cardNum, month, year, owner, cvv);
        clickContinue();
    }

}

