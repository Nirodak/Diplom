## Тестирование формы оплаты тура по дебетовой и кредитной карте
#### ПО необходимое для запуска автотестов:
* Docker
* Удобное для работы с кодом приложение

## Шаги запуска:
#### 1. Клонируем репозиторий
* В приложение для работы с кодом создаём репозиторий.
* В терминале вводим: 
 ```
git clone https://github.com/Nirodak/Diplom.git
 ```
#### 2. Разворачиваем окружение в Docker:
* В командной строке вводим: 
 ```
docker-compose up -d
 ```
#### 3. Запускаем веб сервис:
* БД MySQL: 
 ```
java -jar artifacts/aqa-shop.jar --spring.profiles.active=mysql
 ```
* БД PostgreSQL : 
 ```
java -jar artifacts/aqa-shop.jar --spring.profiles.active=postgresql
 ```
#### 4. Адрес веб-сервиса:
 ```
 http://localhost:8080
 ```
#### 5. Запуск автотестов
* В командной строке вводим: 
 ```
./gradlew clean test allureReport  
 ```
#### 6. Формирование отчёта о результатах автотестов:
* В командной строке вводим: 
 ```
./gradlew allureServe   
 ```
