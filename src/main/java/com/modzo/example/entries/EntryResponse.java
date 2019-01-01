package com.modzo.example.entries;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.modzo.example.entries.domain.Entry;

public class EntryResponse {

    private final long id;
    private final String name;

    @JsonCreator
    public EntryResponse(@JsonProperty("id") long id,
                         @JsonProperty("name") String name) {
        this.id = id;
        this.name = name;
    }

    EntryResponse(Entry entry) {
        this.id = entry.getId();
        this.name = entry.getName();
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
