package ru.job4j.auth.service;

import org.springframework.stereotype.Service;
import ru.job4j.auth.model.Person;
import ru.job4j.auth.store.PersonRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    private final PersonRepository personStore;

    public PersonService(PersonRepository personStore) {
        this.personStore = personStore;
    }

    public List<Person> findAllPerson() {
        return personStore.findAll();
    }

    public Optional<Person> findPersonById(int id) {
        return personStore.findById(id);
    }

    public Person save(Person person) {
        return personStore.save(person);
    }

    public Person update(Person person) {
        return personStore.save(person);
    }

    public void delete(int id) {
        personStore.delete(findPersonById(id).get());
    }


}
