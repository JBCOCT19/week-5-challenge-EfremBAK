package com.example.demo;

import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface Repository extends CrudRepository<Job, Long> {

    ArrayList<Job> findByTitleContainsIgnoreCaseOrAuthorContainsIgnoreCase(String title, String author);
}