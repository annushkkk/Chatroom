# Chatroom

## Реализовано по критериям
### 1. Есть хэширование паролей
### 2. Реализована JWT-аутентификация(для хранения токена используется сессионная cookie)
### 3. Подключён Spring Security 
### 4. Подключена PostgreSQL
### 5. Есть механизмы защиты от обхода разлогина(фильтрация деактивированных токенов)
### 6. Есть API для регистрации, сохраняющий пользователя в хранилище
### Эндпоинт POST ```/api/users```
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
### 7. Есть API, позволяющий изменять базовую информацию профиля, при изменении email есть подтверждение изменения ссылкой на указанный новый email
### Эндпоинт PUT ```/api/users```
Запрос
```http request
PUT /api/users
```
```json
{
    
    "email":"dijix99883@ksyhtc.com",
    "nickname":"useruser",
    "id":"8fc9eb94-2baf-4771-9217-f4a601e01778",
    "lastName": "Ushakova",
    "firstName":"Anna"

    
}
```
Ответ
```json
{
    "email":"dijix99883@ksyhtc.com",
    "nickname":"useruser",
    "id":"8fc9eb94-2baf-4771-9217-f4a601e01778",
    "lastName": "Ushakova",
    "firstName":"Anna"
}
```

### 8. Есть API, позволяющий залогиниться в системе и сохраняющее информацию о сессии
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
### 9. Есть API, позволяющий завершить текущую сессию и разлогиниться
### Эндпоинт POST ```/logout```
По этому запросу удаляется сессионная cookie и деактивируется токен
```http request
POST /logout
```

### 10. Есть подтверждение почты через ссылку в письме, отправленном на указанную почту
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

