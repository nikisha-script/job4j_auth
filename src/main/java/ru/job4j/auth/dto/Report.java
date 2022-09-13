package ru.job4j.auth.dto;

import lombok.*;
import ru.job4j.auth.model.Person;

import java.sql.Timestamp;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@RequiredArgsConstructor
public class Report {

    @EqualsAndHashCode.Include
    private int id;
    @NonNull
    private String name;
    @NonNull
    private Timestamp created;
    @NonNull
    private Person person;

    public static Report of(int id, String name, Person person) {
        Report report = new Report();
        report.id = id;
        report.name = name;
        report.person = person;
        report.created = new Timestamp(System.currentTimeMillis());
        return report;
    }

}
