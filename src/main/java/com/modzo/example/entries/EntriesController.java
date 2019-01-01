package com.modzo.example.entries;

import com.modzo.example.entries.domain.commands.FindLastEntry;
import com.modzo.example.entries.domain.commands.SaveEntry;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
class EntriesController {

    private final FindLastEntry findLastEntry;

    private final SaveEntry saveEntry;

    public EntriesController(FindLastEntry findLastEntry,
                             SaveEntry saveEntry) {
        this.findLastEntry = findLastEntry;
        this.saveEntry = saveEntry;
    }

    @PostMapping("/entries")
    ResponseEntity<EntryResponse> lastEntry(@RequestBody SaveEntryRequest request) {
        SaveEntry.Response response = saveEntry.execute(new SaveEntry.Request(request.getName()));
        return ResponseEntity.created(URI.create("/entries"))
                .body(new EntryResponse(response.getEntry()));
    }

    @GetMapping("/entries/last")
    ResponseEntity<EntryResponse> saveEntry() {
        EntryResponse lastEntry = findLastEntry.execute()
                .map(EntryResponse::new)
                .orElseThrow(() -> new RuntimeException("No last entry"));
        return ResponseEntity.ok(lastEntry);
    }
}
