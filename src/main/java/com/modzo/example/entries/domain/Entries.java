package com.modzo.example.entries.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface Entries extends JpaRepository<Entry, Long> {

    Optional<Entry> findFirstByOrderByIdDesc();
}
