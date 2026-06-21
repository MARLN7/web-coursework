# Онлайн-магазин — Курсовой проект

## Структура проекта

```
shop/
├── backend/          # Java Spring Boot (порт 8080)
│   ├── pom.xml
│   └── src/main/java/com/shop/
│       ├── ShopApplication.java
│       ├── Product.java
│       └── ProductController.java
└── frontend/
    └── index.html    # Одностраничное приложение
```

## Запуск

### Backend (Java 17+, Maven)
```bash
cd backend
mvn spring-boot:run
```
Сервер запустится на http://localhost:8080

### Frontend
Открыть файл `frontend/index.html` в браузере.

## API endpoints

| Метод  | URL                          | Описание              |
|--------|------------------------------|-----------------------|
| GET    | /api/products                | Список всех товаров   |
| POST   | /api/products                | Создать товар         |
| DELETE | /api/products/{id}           | Удалить товар         |
| PATCH  | /api/products/{id}/archive   | Архивировать товар    |

## Требования задания

- ✅ 5+ свойств объекта: название, цена, количество, категория, описание, статус
- ✅ 3 состояния: создание (форма), активные товары (список), архив
- ✅ Адаптивная вёрстка: монитор, планшет, смартфон (media queries)
- ✅ Backend на Java (Spring Boot) отдаёт JSON-массив

