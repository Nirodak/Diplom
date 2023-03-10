package step;
import data.CardDataHelper;
import data.PageElement;
import io.qameta.allure.Step;

public class Steps {
    PageElement pageElement = new PageElement();

    //Форма для заполнения полей
    public void fillField (String cardNum, String month, String year, String owner, String cvv){
        pageElement.getCardNumberField().setValue(cardNum);
        pageElement.getMonthField().setValue(month);
        pageElement.getYearField().setValue(year);
        pageElement.getOwnerField().setValue(owner);
        pageElement.getCvcField().setValue(cvv);
    }

    @Step ("Выбор блока купить")
    public void selectBuy() {
        pageElement.getButtonPay().click();
    }
    @Step ("Выбор блока купить в кредит")
    public void selectBuyCredit () {
        pageElement.getButtonPayCredit().click();
    }

    @Step ("Заполнение валидным значениями")
    public void validDate (){
        String cardNum = CardDataHelper.cardValidApproved();
        String month = CardDataHelper.monthValid();
        String year = CardDataHelper.yearValid();
        String owner = CardDataHelper.ownerValid();
        String cvv = CardDataHelper.cvvValid();
        fillField(cardNum, month, year, owner, cvv);
    }


}
