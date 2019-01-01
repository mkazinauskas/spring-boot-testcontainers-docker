package com.modzo.example.entries.domain.commands;

import com.modzo.example.entries.domain.Entries;
import com.modzo.example.entries.domain.Entry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
public class SaveEntry {

    private Entries entries;

    public SaveEntry(Entries entries) {
        this.entries = entries;
    }

    @Transactional
    public Response execute(Request request){
        Entry entry = new Entry(request.name);
        return new Response(entries.save(entry));
    }

    public static class Request{
        private final String name;

        public Request(String name) {
            this.name = name;
        }
    }

    public static class Response{
        private final Entry entry;

        Response(Entry entry) {
            this.entry = entry;
        }

        public Entry getEntry() {
            return entry;
        }
    }
}
