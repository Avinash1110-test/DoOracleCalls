package com.oracle.application.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/oracle-services/")
public class AppController {

    private static final Logger logger = LoggerFactory.getLogger(AppController.class);

    @GetMapping(value = "health")
    public ResponseEntity<String> healthCheck() {

        logger.info("Health check is called successfully.");
        return new ResponseEntity<>("Application is ready.", HttpStatus.OK);
    }
}
