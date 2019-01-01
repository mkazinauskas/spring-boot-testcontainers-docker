package com.modzo.example.entries.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name = "entries")
public class Entry {
    @Id
    @GeneratedValue(generator = "entries_sequence", strategy = SEQUENCE)
    @SequenceGenerator(
            name = "entries_sequence",
            sequenceName = "entries_sequence",
            allocationSize = 1
    )
    private Long id;

    Entry() {
        // you know... JPA...
    }

    @Column(name = "name")
    private String name;

    public Entry(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}