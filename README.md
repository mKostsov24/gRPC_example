# gRPC_example
GRPC приложения cерверной и клиентской части

Приложение реализовано с использованием:

Java

Spring boot

GRPC

PostgresSQL

Функционал приложения:

Клиент отправляет данные для записи в БД, а также запрашивает список уже существующих записей по критериям: id сообщения, дата/время, тело сообщения, имя очереди

Сервер обрабатывает сообщения и высылает ответы как об успешном добавлении данных в базу, так и о данных из базы по критериям 

Работа с базой раелизована с помощью Spring DATA JPA для получения данных и JDBC для @transactional 
