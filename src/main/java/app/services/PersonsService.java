package app.services;

import app.database.DBException;
import app.database.DatabaseService;
import app.database.entities.User;
import app.models.PersonModel;

import java.sql.SQLException;
import java.util.*;

public class PersonsService {
    Map<String, PersonModel> personsSessions;
    List<PersonModel> persons;
    DatabaseService databaseService;

    public PersonsService(DatabaseService databaseService) {
        persons = new ArrayList<>();
        personsSessions = new HashMap<>();
        this.databaseService = databaseService;
    }

    public PersonModel create(String login, String password, String email) throws SQLException, DBException {
        PersonModel person = new PersonModel(login, password, email);
        if (!this.has(person)) {
            try {
                databaseService.addUser(person);
            } catch (DBException e) {
            }
        }

        return person;
    }

    public void createSession(String sessionKey, PersonModel person) {
        personsSessions.put(sessionKey, person);
    }

    public boolean hasSession(String sessionKey) {
        return personsSessions.containsKey(sessionKey);
    }

    public boolean has(PersonModel personModel) throws DBException {
        User user = databaseService.getUser(personModel.getLogin());

        return user != null;
    }

    public boolean has(String login, String password) throws DBException {
        User user = databaseService.getUser(login);

        return (user != null && Objects.equals(user.getPassword(), password));
    }

    public PersonModel get(String login, String password) throws DBException {
        User user = databaseService.getUser(login);

        if (user != null && Objects.equals(user.getPassword(), password)) {
            return new PersonModel(user.getLogin(), user.getPassword(), user.getEmail());
        }

        return null;
    }

    public boolean hasLogin(String login) throws DBException {
        User user = databaseService.getUser(login);

        return user != null;
    }

    public boolean hasEmail(String email) throws SQLException, DBException {
        User user = databaseService.getUserByEmail(email);

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
