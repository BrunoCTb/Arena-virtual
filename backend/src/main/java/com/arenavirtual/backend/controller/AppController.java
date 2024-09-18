package com.arenavirtual.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController {

    @GetMapping({"/", "/inicio"})
    public ResponseEntity<String> home() {
        return ResponseEntity.ok("Olá! Seja bem vindo a Arena Virtual");
    }

}
