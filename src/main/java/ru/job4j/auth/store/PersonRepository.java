package ru.job4j.auth.store;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.job4j.auth.model.Person;


@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {

}
