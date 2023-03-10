package data;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.Data;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

@Data
public class PageElement {

    private final SelenideElement buttonPay = $(byText("Купить"));
    private final SelenideElement buttonPayCredit = $(byText("Купить в кредит"));
    private final SelenideElement cardNumberField = $(byText("Номер карты")).parent().$(".input__control");//Поле номер карты
    private final SelenideElement monthField = $(byText("Месяц")).parent().$(".input__control");//Поле месяц
    private final SelenideElement yearField = $(byText("Год")).parent().$(".input__control");// Поле год
    private final SelenideElement ownerField = $(byText("Владелец")).parent().$(".input__control");// Поле владлец
    private final SelenideElement cvcField = $(byText("CVC/CVV")).parent().$(".input__control");//Поле CVV
    private final SelenideElement continueButton = $(byText("Продолжить")).parent().parent();//Кнопка продолжить
    private final SelenideElement successOperation = $(withText("Операция одобрена Банком."));//Нот успешно
    private final SelenideElement errorOperation = $(withText("Ошибка! Банк отказал в проведении операции."));//Нот ошибка
    private final ElementsCollection errorText = $$(".input__sub");
}
