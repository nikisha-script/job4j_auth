package ru.job4j.auth.controller;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import ru.job4j.auth.model.Person;
import ru.job4j.auth.dto.Report;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/report")
public class ReportsController {

    private static final String API = "http://localhost:8189/person/";
    private static final String API_ID = "http://localhost:8189/person/{id}";
    private final RestTemplate rest;

    public ReportsController(RestTemplate rest) {
        this.rest = rest;
    }

    @GetMapping()
    public List<Report> findAll() {
        List<Report> rsl = new ArrayList<>();
        List<Person> persons = rest.exchange(
                API,
                HttpMethod.GET, null, new ParameterizedTypeReference<List<Person>>() { }
        ).getBody();
        persons.forEach(elem -> {
            Report report = Report.of(elem.getId(), elem.getLogin(), elem);
            rsl.add(report);
        });
        return rsl;
    }

    @GetMapping("{id}")
    public Report findById(@PathVariable("id") int id) {
        Person remote = rest.getForObject(API_ID, Person.class, id);
        assert remote != null;
        return Report.of(remote.getId(), remote.getLogin(), remote);
    }

    @PostMapping("/")
    public ResponseEntity<Person> create(@RequestBody Person person) {
        Person rsl = rest.postForObject(API, person, Person.class);
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
