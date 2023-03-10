package data;

import com.github.javafaker.Faker;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
public class CardDataHelper {
   

    //Карта валидная активная
    public static String cardValidApproved() {
        return "4444444444444441";
    }

    //Карта валидная неактивная
    public static String cardValidDeclined() {
        return "4444444444444442";
    }

    //Генерация невалидной карты
    public static String cardNotValid() {
        Faker faker = new Faker();
        String i = Long.toString(faker.number().randomNumber(16, true));
        while (i == cardValidApproved() || i == cardValidDeclined())
            i = Long.toString(faker.number().randomNumber(16, true));
        return i;
    }

    //Валидный месяц
    public static String monthValid() {
        LocalDate date = LocalDate.now();
        return String.format("%tm", date.plusMonths(1));
    }

    //месяц больше 12
    public static String monthMore12() {
        return "13";
    }

    //месяц 00
    public static String monthZero() {
        return "00";
    }

    //Валидный год
    public static String yearValid() {
        LocalDate date = LocalDate.now();
        return String.format("%ty", date.plusYears(1));
    }

    //Просроченный год
    public static String yearOverdue() {
        LocalDate date = LocalDate.now();
        return String.format("%ty", date.minusYears(1));
    }

    //Невозможный год для карты
    public static String yearExceeded() {
        LocalDate date = LocalDate.now();
        return String.format("%ty", date.plusYears(6));
    }

    //Валидный пользователь
    public static String ownerValid() {
        Faker faker = new Faker();
        String owner = String.valueOf(faker.name().name());
        return owner;
    }

    //Пользователь на кириллице
    public static String ownerKirillica() {
        return "Петров Иван";
    }

    //Пользователь со спец символами
    public static String ownerSpecSymbol() {
        return "%&^!@#";
    }

    //cvv валидное
    public static String cvvValid() {
        Faker faker = new Faker();
        int i = faker.number().numberBetween(100, 999);
        return Integer.toString(i);

    }

    //cvv 000
    public static String cvvZero() {
        return "000";
    }

    //Пустое поле
    public static String empty() {
        return "";
    }

}
