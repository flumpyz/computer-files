package app.services;

import app.models.PersonModel;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class PersonsService {
    Map<String, PersonModel> personsSessions;
    List<PersonModel> persons;
    Connection connection;

    public PersonsService(Connection connection) {
        persons = new ArrayList<>();
        personsSessions = new HashMap<>();
        this.connection = connection;
    }

    public PersonModel create(String login, String password, String email) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            PersonModel person = new PersonModel(login, password, email);
            if (!this.has(person)) {
                statement.execute(
                        String.format("insert into web_application.registered_users (Login, Password, Email) values ('%s', '%s', '%s')", login, password, email));
            }

            return person;
        }
    }

    public void createSession(String sessionKey, PersonModel person) {
        personsSessions.put(sessionKey, person);
    }

    public boolean hasSession(String sessionKey) {
        return personsSessions.containsKey(sessionKey);
    }

    public boolean has(PersonModel personModel) throws SQLException {
        PersonModel user = null;
        String login = personModel.getLogin();
        String password = personModel.getPassword();
        String email = personModel.getEmail();

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(
                    String.format("select * from web_application.registered_users where Login = '%s' and Password = '%s' and Email = '%s'", login, password, email));

            if (resultSet.next() && resultSet.isLast()) {
                String personLogin = resultSet.getString("Login");
                String personPassword = resultSet.getString("Password");
                String personEmail = resultSet.getString("Email");

                user = new PersonModel(personLogin, personPassword, personEmail);
            }
        }

        return user != null;
    }

    public boolean has(String login, String password) throws SQLException {

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(
                    String.format("select * from web_application.registered_users where Login = '%s' and Password = '%s'", login, password));

            if (resultSet.next() && resultSet.isLast()) {
                return true;
            }
        }

        return false;
    }

    public PersonModel get(String login, String password) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(
                    String.format("select * from web_application.registered_users where Login = '%s' and Password = '%s'", login, password));

            if (resultSet.next() && resultSet.isLast()) {
                String personLogin = resultSet.getString("Login");
                String personPassword = resultSet.getString("Password");
                String personEmail = resultSet.getString("Email");

                return new PersonModel(personLogin, personPassword, personEmail);
            }
        }

        return null;
    }

    public boolean hasLogin(String login) throws SQLException {
        PersonModel user = null;
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(
                    String.format("select * from web_application.registered_users where Login = '%s'", login));

            if (resultSet.next() && resultSet.isLast()) {
                String personLogin = resultSet.getString("Login");
                String personPassword = resultSet.getString("Password");
                String personEmail = resultSet.getString("Email");

                user = new PersonModel(personLogin, personPassword, personEmail);
            }
        }

        return user != null;
    }

    public boolean hasEmail(String email) throws SQLException {
        PersonModel user = null;

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(
                    String.format("select * from web_application.registered_users where Email = '%s'", email));

            if (resultSet.next() && resultSet.isLast()) {
                String personLogin = resultSet.getString("Login");
                String personPassword = resultSet.getString("Password");
                String personEmail = resultSet.getString("Email");

                user = new PersonModel(personLogin, personPassword, personEmail);
            }
        }

        return user != null;
    }

    public void removeSession(String sessionKey) {
        personsSessions.remove(sessionKey);
    }

    public String getLoginBySessionKey(String sessionKey) {
        if (personsSessions.containsKey(sessionKey)) {
            return personsSessions
                    .get(sessionKey)
                    .getLogin();
        }

        return null;
    }
}
