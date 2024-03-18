# REST API Spring
## Rest Api for [jsonplaceholder](jsonplaceholder.typicode.com)

### Созданное Rest Api с взаимодействеим с БД на PostgresSQL.
Реализованно:
 - Регистарция и авторизация пользователя по средтву JWT токена (данные о пользователе хранятся в БД, пароль так же шифруется)
 - Ролевая модель доступа
   - ROLE_ADMIN: Доступ ко всем возможностям
   - ROLE_POSTS: Доступ ко `/posts/**`
   - ROLE_ALBUMS: Доступ ко `/albums/**`
   - ROLE_USERS: Доступ ко `/users/**`
 - POST, GET, PATCH, PUT, DELETE запросы для сервиса [jsonplaceholder](jsonplaceholder.typicode.com)
 - Запросы с фильтрацией или ограниченями (возможности [jsonplaceholder](jsonplaceholder.typicode.com))
   
Планируется до реализовать:
 - Кэширование запросов
 - Логирование
 - Расширеная ролевая модель 


