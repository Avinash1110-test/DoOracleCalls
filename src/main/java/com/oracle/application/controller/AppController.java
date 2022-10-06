package com.oracle.application.controller;

import com.oracle.application.service.AppService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/oracle-services/")
public class AppController {

    private static final Logger logger = LoggerFactory.getLogger(AppController.class);

    @Autowired
    private AppService appService;

    @GetMapping(value = "health")
    public ResponseEntity<String> healthCheck() {

        logger.info("AppController - Health check is called successfully.");
        return new ResponseEntity<>("Application is ready.", HttpStatus.OK);
    }

    @GetMapping(value = "getUserPrimaryAddress/{userId}")
    public ResponseEntity<String> getUserPrimaryAddress(@PathVariable("userId") String userId) {

        logger.info("AppController - getUserPrimaryAddress is called with input: {}", userId);
        String response = appService.getUserPrimaryAddress(userId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
