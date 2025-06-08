# Car Repair Shop

---

## Стек технологий

- **Java 21**
- **Spring Boot 3.4.2** (Web, Security, Validation)
- **Spring Data JPA** + PostgreSQL
- **Spring Kafka** (Consumer + Producer)
- **Lombok**, **MapStruct**
- **Swagger**
- **Docker, Docker Compose**

---

## Как запустить

1. Клонировать проект:

   ```bash
   git clone https://github.com/laldil/car-repair-shop.git
   ```
2. Запустить проект
   ```
   docker-compose up
   ```
3. Приложение будет доступно по адресам:
- API: http://localhost:8080
- Swagger: http://localhost:8080/swagger-ui/index.html
- PostgreSQL: localhost:5433
- Kafka (KRaft): localhost:9092

---

## 📬 Postman коллекция

В репозитории доступна готовая коллекция запросов для Postman:

- `postman/car-repair-shop.postman_collection.json`

---

##  Основные особенности


### Авторизация и регистрация

- JWT-авторизация с access и refresh токенами
- Пароли шифруются через BCrypt
- Роли (`ADMIN`, `CLIENT`, `EMPLOYEE`)
- Доступы контролируются через аннотации `@PreAuthorize`

---

### Работа с заказами

- Создание, просмотр и фильтрация заказов по статусу и пользователю
- У каждого заказа статус: `NEW → ACCEPTED → IN_PROGRESS → REPAIR → COMPLETED`

---

### История статусов
- При каждом изменении статуса создаётся запись в истории (OrderStatusHistory)
- История сохраняется через `@TransactionalEventListener` — только если транзакция коммитится

---

### ️ Уведомления
- При переходе в `COMPLETED` клиент уведомляется через `NotificationService`
- Тип уведомления выбирается из NotificationType (EMAIL, SMS, etc.)

---

### Kafka-интеграция
- Поддержка входящих Kafka-команд на смену статуса
- Читает order-status-commands и вызывает OrderService
- Топики создаются через KafkaConfig (NewTopic)
- При изменении статуса, заявка проверяется на идемпотентность через `ProcessedStatusChangeCommandRepository`
