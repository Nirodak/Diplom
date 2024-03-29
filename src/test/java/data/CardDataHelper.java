package data;

import com.github.javafaker.Faker;
import lombok.Data;

import java.time.LocalDate;
import java.util.Locale;

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
    public static String cardInValid() {
        Faker faker = new Faker();
        String i = Long.toString(faker.number().randomNumber(16, true));
        while (i == cardValidApproved() || i == cardValidDeclined())
            i = Long.toString(faker.number().randomNumber(16, true));
        return i;
    }

    //Текущий месяц +1
    public static String monthPlusOne() {
        LocalDate date = LocalDate.now();
        return String.format("%tm", date.plusMonths(1));
    }

    //Текущий месяц -1
    public static String monthMinusOne() {
        LocalDate date = LocalDate.now();
        return String.format("%tm", date.minusMonths(1));
    }

    //Месяц больше 12
    public static String monthMore12() {
        return "13";
    }

    //месяц 00
    public static String monthZero() {
        return "00";
    }

    //Год +1
    public static String yearPlusOne() {
        LocalDate date = LocalDate.now();
        return String.format("%ty", date.plusYears(1));
    }

    //Текущий год
    public static String yearCurrent() {
        LocalDate date = LocalDate.now();
        return String.format("%ty", date);
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
        Faker faker = new Faker(new Locale("en"));
        String owner = String.valueOf(faker.name().firstName().replaceAll("'", "")  + " "  + faker.name().lastName().replaceAll("'", ""));
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
    //Пользователь цифры
    public static String ownerNumber (){
        return "123";
    }

    //cvv валидное
    public static String cvvValid() {
        Faker faker = new Faker();
        int i = faker.number().numberBetween(100, 999);
        return Integer.toString(i);

    }

    // Номер карты меньше 16 символов
    public static String cardCharLessRequired() {
        Faker faker = new Faker();
        long i = faker.number().randomNumber(15, false);
        return Long.toString(i);
    }

    // Месяц меньше 2 символов
    public static String monthCharLessRequired() {
        Faker faker = new Faker();
        long i = faker.number().randomNumber(1, true);
        return Long.toString(i);
    }

    // Год меньше 2 символов
    public static String yearCharLessRequired() {
        Faker faker = new Faker();
        long i = faker.number().randomNumber(1, true);
        return Long.toString(i);
    }

    // CVV меньше 3 символов
    public static String cvvCharLessRequired() {
        Faker faker = new Faker();
        long i = faker.number().randomNumber(2, true);
        return Long.toString(i);
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
