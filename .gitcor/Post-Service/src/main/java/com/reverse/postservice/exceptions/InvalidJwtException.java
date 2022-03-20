package com.reverse.postservice.exceptions;

import com.reverse.postservice.tools.Log;

public class InvalidJwtException extends Exception{
    public InvalidJwtException(String message) {
        super(message);
        Log.getLog().error(message);
    }
}
