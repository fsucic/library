## READ ME

## Installation

For this application you need to have the following things on your machine:

- java - openjdk (>= 17.0.3)
- Apache Maven (>=3.8.6)
- mysql (>=8.0.29)

### Database Setup

Create a new mysql user and a new mysql database called "library". Grant the user the required permissions (recommended
all) on the database "library".

```
>> sudo mysql -u root
mysql> CREATE DATABASE library;
mysql> CREATE USER your_username@localhost IDENTIFIED BY 'password';
mysql> GRANT ALL PRIVILEGES ON library.* TO your_username@localhost WITH GRANT OPTION;
mysql> FLUSH PRIVILEGES;
>> mysql -u your_username -p
mysql> USE DATABASE library;
```

Shown above is one way of how to do it. The last command is just so you can enter the database and query it how you
wish.

Next, in the 'src/main/resources/application.properties' file configure your database so that the application connects
to it. I recommend that you leave jpa.hibernate.ddl-auto on 'update'.

### Application Setup

In the project directory, use maven to download all dependencies and build the project.

```
>> mvn clean install
```

Everything should be in order once you do this and you can start your application. The application will build the
database schema on its own.

### Application Use

The api will be exposed on port 8080 and you will be able to send http requests to it. Have a look at the controllers
and request classes for more details, the specifics...

### General Comments

- You can view the database architecture by viewing the Model classes, they are mapped to tables in the database.
- I am sure there are bugs in the application. The assignment was large and I didn't have the time to check everything
  nor write tests for everything. Moreover, there are flaws in the DB design as well, but again, I didn't have the time
  to reconfigure, and this works well enough.
- The funny ViewModelView classes are here as view classes for other view classes as otherwise infinite recursion would
  occur.
- The main feature I left out is security, there is a branch on the repository called 'security' which you can access
  and is a work in progress...
- Thank you for your time!

### Authentication and Authorization

The library catalogue is available to the general public, non-registered users can access:

- the list of all books
- individual book details
- list of all authors
- individual autor details
- search
- member registration (POST request to "/member")

For other services, member have to login at url "/login" where they will receive a token. This token has to be added to
HTTP headers as "Bearer". For now there is no distinction between member and admin, but in the future this could be
achieved bz adding roles or groups.