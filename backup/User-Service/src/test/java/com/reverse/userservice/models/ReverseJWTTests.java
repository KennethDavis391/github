package com.reverse.userservice.models;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.crypto.SecretKey;
import java.lang.reflect.Field;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class ReverseJWTTests {

    private ReverseJWT testReverseJWT;

    private Long userID = 10L;
    private String secret = "g9vcBtznWr+HpLBjBi3IW/cOOd8gjnJYJ32ftiNDpBBPtcHO3ac/4IiK4eCz8x4xlEH0o6E53tS8UVOSQyY+yg==";

    @BeforeEach
    public void init() {
        testReverseJWT = new ReverseJWT(userID, secret);
    }

    @Test
    public void generateTest() {
        ReverseJWT newReverseJWT = new ReverseJWT(userID, secret);
        //They should be different because they were created at different times
        assertNotEquals(newReverseJWT.getToken(), testReverseJWT.getToken(), "newReverseJWT token and testReverseJWT token are the same!");
    }

    @Test
    public void testGetUserIDGood() throws NoSuchFieldException, IllegalAccessException {
        SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));

        String token = Jwts.builder().setSubject(String.valueOf(userID)).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 3600 * 1000)).signWith(key, SignatureAlgorithm.HS512).compact();

        Field field = ReverseJWT.class.getDeclaredField("token");
        field.setAccessible(true);
        field.set(testReverseJWT, token);

        assertEquals(userID, testReverseJWT.getUserID(secret), "Incorrect userID returned!");
    }

    @Test
    public void testGetUserIDBadSignature() throws NoSuchFieldException, IllegalAccessException {
        SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode("g9vcBtznWr+HpLBjBi3IW/cOOd8gjnJYJ32ftiNDpBBPtcHO3ac/4IiK4eCz8x4ABC1236E53tS8UVOSQyY+yg=="));

        String token = Jwts.builder().setSubject(String.valueOf(userID)).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 3600 * 1000)).signWith(key, SignatureAlgorithm.HS512).compact();

        Field field = ReverseJWT.class.getDeclaredField("token");
        field.setAccessible(true);
        field.set(testReverseJWT, token);

        assertNull(testReverseJWT.getUserID(secret), "Bad signature not returning null!");
    }

    @Test
    public void testGetUserIDBadExp() throws NoSuchFieldException, IllegalAccessException {
        SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));

        String token = Jwts.builder().setSubject(String.valueOf(userID)).setIssuedAt(new Date(System.currentTimeMillis() - 1000))
                .setExpiration(new Date(System.currentTimeMillis() - 500)).signWith(key, SignatureAlgorithm.HS512).compact();

        Field field = ReverseJWT.class.getDeclaredField("token");
        field.setAccessible(true);
        field.set(testReverseJWT, token);

        assertNull(testReverseJWT.getUserID(secret));
    }

    @Test
    public void testGetUserIDBadVal() throws NoSuchFieldException, IllegalAccessException {
        SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));

        String token = Jwts.builder().setSubject("ABC").setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 3600 * 1000)).signWith(key, SignatureAlgorithm.HS512).compact();

        Field field = ReverseJWT.class.getDeclaredField("token");
        field.setAccessible(true);
        field.set(testReverseJWT, token);

        assertNull(testReverseJWT.getUserID(secret));
    }

    @Test
    public void testGenAndGetUserID() {
        new ReverseJWT(userID, secret);
        assertEquals(userID, testReverseJWT.getUserID(secret), "UserID and retreved id do not match!");
    }

}
