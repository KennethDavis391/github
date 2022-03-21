package com.reverse.postservice.controllers;

import com.reverse.postservice.exceptions.InvalidJwtException;

public interface ValidationUtils {
    void validateJwt(String token) throws InvalidJwtException;
}
