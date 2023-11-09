package com.example.gracefulshutdown.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/graceful")
public class Controller {

    @GetMapping("/{number}")
    public ResponseEntity<Void> test(@PathVariable final int number) throws InterruptedException {
        System.out.println("start with " + number);
        Thread.sleep(30_000);
        System.out.println("end with" + number);
        return ResponseEntity.status(HttpStatus.OK.value()).build();
    }
}
