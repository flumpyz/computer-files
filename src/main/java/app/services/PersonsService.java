package app.services;

import app.models.PersonModel;

import java.util.*;

public class PersonsService {
    Map<String, PersonModel> personsSessions;
    List<PersonModel> persons;

    public PersonsService() {
        persons = new ArrayList<>();
        personsSessions = new HashMap<>();
    }

    public PersonModel create(String login, String password, String email) {
        PersonModel person = new PersonModel(login, password, email);
        if (!this.has(person)) {
            persons.add(new PersonModel(login, password, email));
        }

        return person;
    }

    public void createSession(String sessionKey, PersonModel person) {
        personsSessions.put(sessionKey, person);
    }

    public boolean hasSession(String sessionKey) {
        return personsSessions.containsKey(sessionKey);
    }

    public boolean has(PersonModel personModel) {
        for (PersonModel person: persons
             ) {
            if (Objects.equals(personModel.getLogin(), person.getLogin())
                    && Objects.equals(personModel.getPassword(), person.getPassword())
                    && Objects.equals(personModel.getEmail(), person.getEmail())) {
                return true;
            }
        }

        return false;
    }

    public boolean has(String login, String password) {
        for (PersonModel person: persons
        ) {
            if (Objects.equals(login, person.getLogin())
                    && Objects.equals(password, person.getPassword())) {
                return true;
            }
        }

        return false;
    }

    public PersonModel get(String login, String password) {
        for (PersonModel person: persons
             ) {
            if (person.getLogin() == login
                    && person.getPassword() == password) {
                return person;
            }
        }

        return null;
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
