package com.modzo.example.entries.domain.commands;

import com.modzo.example.entries.domain.Entries;
import com.modzo.example.entries.domain.Entry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
public class FindLastEntry {

    private Entries entries;

    public FindLastEntry(Entries entries) {
        this.entries = entries;
    }

    @Transactional(readOnly = true)
    public Optional<Entry> execute(){
        return entries.findFirstByOrderByIdDesc();
    }
}
