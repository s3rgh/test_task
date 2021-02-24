# Тестовое задание

1. Вам предлагается развернуть на сервере или локально любую из выбранных вами БД (но предлагаю выбрать что-то более современное). При помощи браузера Google Chrome под управлением Selenium, собрать данные из таблицы, находящейся тут http://checkme.kavichki.com/ в вашу таблицу на сервере, распарсив данные. Далее заполнить некоторыми данными таблицу на сайте Сheckme, сравнить ее результаты с вашей БД и с теми данными, которые вы на самом деле вводили и вывести результат об изменениях.
+ можно несколько функциональных UI тестов на ваше усмотрение по этой таблице на сайте. Код желательно написать на Python или Java с использованием любого фреймворка на ваше усмотрение.

2. У вас есть описанные методы API, расположенные тут — http://jsonplaceholder.typicode.com/ Используйте документ для того, чтобы покрыть автотестами 1 любой GET метод и 1 любой POST метод. Написать код на Python или Java.
   
## Описание

`constants/Endpoints.java`  -

`entity/Purchase.java`      - 

`pages/ShoppingListPage.java` - 

`repository/ShoppingListBD.java` - 

`util/HibernateUtil.java` - 

`runner/RunTest.java` - 

`src/test/java/steps` - 

`src/test/resources/features` - 

`src/test/resources/hibernate.cfg.xml` -

###Для запуска установить

- [MySQL click for Win](https://dev.mysql.com/downloads/installer/)
  
- [MySQL for Linux](https://losst.ru/ustanovka-mysql-ubuntu-16-04) :


- обновите списки пакетов

 `sudo apt update`
- установите необходимые пакеты
  
`sudo apt install mysql-server mysql-client`
- проверить установленную версию
  
`mysql -V`
  
 - запущена ли служба MySQL
   
`sudo systemctl status mysql`

- перед тем как полноценно использовать установленную бд, необходимо выполнить ее первоначальную настройку
  
`sudo mysql_secure_installation`
  
 - подключиться пользователем root к серверу баз данных из командной строки

`sudo mysql -u root`

- создаем тестовую базу данных

`mysql> CREATE DATABASE testDB;`


## Для проверки тестов

Установить maven
Установить git 
Хотя вряд ли это не установлено

Выполнить

`git clone https://github.com/s3rgh/test_task.git`

`mvn clean install`

`mvn allure:serve`