package ru.job4j.auth.controller;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import ru.job4j.auth.dto.Employee;
import ru.job4j.auth.model.Person;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final RestTemplate rest;
    private static final String API = "http://localhost:8189/person/";
    private static final String API_ID = "http://localhost:8189/person/{id}";

    public EmployeeController(RestTemplate rest) {
        this.rest = rest;
    }

    @GetMapping()
    public List<Employee> findAll() {
        List<Employee> rsl = new ArrayList<>();
        List<Person> persons = rest.exchange(
                API,
                HttpMethod.GET, null, new ParameterizedTypeReference<List<Person>>() { }
        ).getBody();
        persons.forEach(elem -> {
            Employee emp = Employee.of(elem.getId(), elem.getLogin(), elem.getLogin(), elem.getPassword(), persons);
            rsl.add(emp);
        });
        return rsl;
    }

    @GetMapping("{id}")
    public Employee findById(@PathVariable("id") int id) {
        return rest.getForObject(API_ID, Employee.class, id);
    }

    @PostMapping("/")
    public ResponseEntity<Employee> create(@RequestBody Person person) {
        Employee rsl = rest.postForObject(API, person, Employee.class);
        return new ResponseEntity<>(
                rsl,
                HttpStatus.CREATED
        );
    }

    @PutMapping("/")
    public ResponseEntity<Void> update(@RequestBody Person person) {
        rest.put(API, person);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        rest.delete(API_ID, id);
        return ResponseEntity.ok().build();
    }
}
