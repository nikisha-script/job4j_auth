package ru.job4j.auth.dto;

import lombok.*;
import ru.job4j.auth.model.Person;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Employee {

    @EqualsAndHashCode.Include
    private int id;
    @NonNull
    private String name;
    @NonNull
    private String surname;
    @NonNull
    private String taxpayerIdentificationNumber;

    private LocalDateTime created;

    private List<Person> personList;


    public static Employee of(int id, String name, String surname, String taxpayerIdentificationNumber, List<Person> personList) {
        Employee employee = new Employee();
        employee.id = id;
        employee.name = name;
        employee.surname = surname;
        employee.taxpayerIdentificationNumber = taxpayerIdentificationNumber;
        employee.personList = personList;
        employee.created = LocalDateTime.now();
        return employee;
    }
}
