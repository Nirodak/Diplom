## Тестирование формы оплаты тура по дебетовой и кредитной карте
#### ПО необходимое для запуска автотестов:
* Docker
* Удобное для работы с кодом приложение

## Шаги запуска:
* Клонируем репозиторий
* В командной строке вводим: docker-compose up -d
* Запускаем jar файл командной для тестирования на БД mysql: java -jar artifacts/aqa-shop.jar --spring.profiles.active=mysql
* Запуск автотестов командой: ./gradlew clean test allureReport   
* Формирование отчёта о результатах автотестов: ./gradlew allureServe   

## Покрытый автотестами функционал:
### Формы заполнения дебетовой и кредитной карты по следующим параметрам:
#### Проверка заполнения формы валидными значениями:
* с картой в статусе Approved 
* с картой в статусе Declined
#### Проверка поля номер карты с использованием невалидных значений:
* Номер карты меньше 16 символов
* Номер карты, который отсутствует в БД
* Пустое поле 
#### Проверка поля месяц с использованием невалидных значений:
* Значения месяца больше 12
* Значение месяца равно 00
* Срок действия карты просрочен по месяцу
* Значение месяца меньше 2 символов
* Пустое поле
#### Проверка поля года с использованием невалидных значений:
* Год действия карты просрочен
* Год действия карты больше 6 лет
* Значение года меньше 2 символов
* Пустое поле
#### Проверка поля владелец с использованием невалидных значений:
* Ввод владельца на кириллице
* Ввод владельца используя спец. символы
* Пустое поле
#### Проверка поля cvv с использованием невалидных значений:
* Заполнение  поля cvv меньше 3 символов
* Заполнение поля cvv значением 000
* Пустое поле