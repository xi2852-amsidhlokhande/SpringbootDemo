package com.amsidh.mvc.controller;

import com.amsidh.mvc.response.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
@Slf4j
public class PersonController {

    @GetMapping("/api/person/async")
    @Async("asyncExecutor")
    public CompletableFuture<Person> getPersonAsync() throws InterruptedException {
        log.info("getPersonAsync method called");
        for (int i = 0; i < 30; i++) {
            log.info("Async Counter {}", i);
            Thread.sleep(1000);
        }  //Internal Delay
        log.info("Returning response");
        return CompletableFuture.completedFuture(Person.builder().id(123).name("Amsidh-Async").build());
    }

    @GetMapping("/api/person/sync")
    public Person getPersonSync() {
        log.info("getPersonSync method called");
        return Person.builder().id(123).name("Amsidh-Sync").build();
    }
}
