package com.reverse.postservice.controllers;

import com.reverse.postservice.exceptions.InvalidJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Objects;

@Slf4j
@Service
public class ValidationUtilsImpl implements ValidationUtils{

    private static final String validationAddress = System.getenv("VALIDATION");

    //private WebClient client;

    //@Autowired
    //public ValidationUtilsImpl(WebClient webClient){
    //    this.client = webClient;
    //}

    public void validateJwt(String token) throws InvalidJwtException {
        token = token.split("Bearer ")[1];

        log.debug(validationAddress);

        WebClient client = WebClient.create();

        ResponseEntity<String> response = client
                .post()
                .uri(validationAddress)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue("{\"token\":" + token + "}")
                .retrieve().toEntity(String.class)
                .block();

        if(Objects.requireNonNull(response).getStatusCodeValue() != 200) {
            throw new InvalidJwtException("Invalid JWT received");
        }
    }
}