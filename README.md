# Chatroom
##[Собирается с docker-compose.yml](https://github.com/annushkkk/Chatroom/blob/main/docker-compose.yml)
## Реализовано по критериям
### 1. Есть хэширование паролей
### 2. Реализована JWT-аутентификация(для хранения токена используется сессионная cookie)
### 3. Подключён Spring Security 
### 4. Подключена PostgreSQL
### 5. Подключен Swagger
### 6. Подключен Liquibase
### 7. Есть механизмы защиты от обхода разлогина(фильтрация деактивированных токенов)
### 8. Есть API для регистрации, сохраняющий пользователя в хранилище
### Эндпоинт  ```POST /api/users```
Запрос
```http request
POST /api/users
```
```json
{
    
    "email":"dijix99883@ksyhtc.com",
    "nickname":"useruser",
    "lastName": "Ushakova",
    "firstName":"Anna",
    "password":"password123"
    
}
```
Ответ
```json
{
	"message": "You need to confirm your email"
}
```
Осуществляется проверка на уникальность логина и email:
- 400 - username уже существует
- 400 - email уже существует
### 9. Есть API, позволяющий изменять базовую информацию профиля, при изменении email есть подтверждение изменения ссылкой на указанный новый email
### Эндпоинт  ```PUT /api/users```
Запрос
```http request
PUT /api/users
```
```json
{
    
    "email":"dijix99883@ksyhtc.com",
    "nickname":"useruser",
    "lastName": "Ushakova",
    "firstName":"Anna"

    
}
```
Ответ
```json
{
    "email":"dijix99883@ksyhtc.com",
    "nickname":"useruser",
    "lastName": "Ushakova",
    "firstName":"Anna"
}
```

### 10. Есть API, позволяющий залогиниться в системе и сохраняющее информацию о сессии
К запросу добавляется заголовок с Base64-закодированной строкой, содержащей логин и пароль
```http request

Authorization: Basic dXNlcm5hbWU6cGFzc3dvcmQ=
```
Если данные валидны сервер прикрепляет сессионную cookie
```json
{
  __Host-auth-token=eyJraWQiOiJlMjM1Y2Y5My1lMmY2LTQ2NzktYTI2Ni04MjUxNzdjNGY5MDgiLCJlbmMiOiJBMTI4R0NNIiwiYWxnIjoiZGlyIn0..8BpaxXVOZAK6l8jR.vaOvv6rDmZQaHCxbWktSf26M052vzLhWfl7C2a1roQSTlCun0PZceh3ZLPkQxrFqpdaQZyMQxUhgQ8XR1QtfItcLTiFy3eRJdMvussitMDFB1E5aq0plDbEPhQfYMGNQHg5ciLgJDbPYaoX6NEiTU3EBq20C5UpR.ja2yr735cZulkAa0peyQCg;
  Path=/; Secure; HttpOnly; Expires=Tue, 17 Oct 2023 04:31:00 GMT;
}
```
### 11. Есть API, позволяющий завершить текущую сессию и разлогиниться
### Эндпоинт  ```POST /logout```
По этому запросу удаляется сессионная cookie и деактивируется токен
```http request
POST /logout
```

### 12. Есть подтверждение почты через ссылку в письме, отправленном на указанную почту
### Эндпоинт ```GET /api/tokens/verify/?token={token}```

Запрос
```http request
GET /api/tokens/verify/?token=retertewrtewrtewstte
```
Ответ
```json
{
    "message" : "Account has been successfully created"
}
```
### 13. Есть API, позволяющий обновить пароль
### Эндпоинт  ```PUT /api/users/upd_password```
Запрос
```http request
PUT /api/users/upd_password
```
```json
{
    
    "email":"dijix99883@ksyhtc.com",
    "password":"password123",
    "newPassword": "newpassword123"

    
}
```
Ответ
```json
{
    "message": "Password successfully updated"
}
```
### 14. Есть API, позволяющий удалить аккаунт пользователя;
### Эндпоинт ```DELETE /api/users```

Запрос
```http request
DELETE /api/users
```
Ответ
```json
{
    "message" : "Account successfully deleted"
}
```
### 15. Есть API c возможностью восстановить профиль втечение некоторого времени
### Эндпоинт  ```POST /api/users/recover_account```
Запрос
```http request
POST /api/users/recover_account
```
```json
{

  "nickname":"useruser",
  "password":"password123"

    
}
```
Ответ
```json
{
    "message": "Password successfully recovered"
}
```
### 16. Обмен и просмотр сообщений реализован с помощью веб-сокетов
SockJS используется для установления соединения с веб-сокет сервером, STOMP используется для отправки и получения сообщений через веб-сокет
### Эндпоинт  ``` /ws``` открывает wss соединение

### 17. Есть API, позволяющий отправить другому пользователю сообщение, реализована проверка на существование пользователя
### Эндпоинт  ```/app/chat```

```json
{
  "content": "messageText",
  "senderNickname": "senderNickname" ,
  "recipientNickname": "recipientNickname",
  "timestamp": "2023-10-19 08:46:03.556000"
}
```
### Эндпоинт  ```/user/{userId}/queue/messages``` используется для подписки на сообщения

### 18. Есть API, позволяющий просматривать историю сообщений с конкретным пользователем
### Эндпоинт  ```/app/history```

```json
{

  "recipientNickname": "recipientNickname",
  "pageNumber": 0,
  "pageSize": 2

    
}
```

В качестве ответа на ```/user/{senderId}/queue/messages``` приходит массив сообщений, размер которого указан в pageSize
