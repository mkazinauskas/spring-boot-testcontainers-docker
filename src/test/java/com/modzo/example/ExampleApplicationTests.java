package com.modzo.example;

import com.modzo.example.entries.EntryResponse;
import com.modzo.example.entries.SaveEntryRequest;
import com.modzo.example.entries.domain.Entries;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
@ActiveProfiles(value = "test")
public class ExampleApplicationTests {

    @Autowired
    private Entries entries;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void shouldRetrieveSavedEntry() {
        ResponseEntity<EntryResponse> savedEntry = saveEntry("new entry");

        assertEquals(HttpStatus.CREATED, savedEntry.getStatusCode());
        assertTrue(savedEntry.getBody().getId() > 0L);
        assertEquals("new entry", savedEntry.getBody().getName());

        ResponseEntity<EntryResponse> retrievedEntry = lastEntry();

        assertEquals(HttpStatus.OK, retrievedEntry.getStatusCode());
        assertEquals((long) savedEntry.getBody().getId(), retrievedEntry.getBody().getId());
        assertEquals("new entry", retrievedEntry.getBody().getName());
    }

    private ResponseEntity<EntryResponse> lastEntry() {
        return restTemplate.getForEntity("/entries/last", EntryResponse.class);
    }

    private ResponseEntity<EntryResponse> saveEntry(String name) {
        SaveEntryRequest request = new SaveEntryRequest(name);
        return restTemplate.postForEntity("/entries", request, EntryResponse.class);
    }
}