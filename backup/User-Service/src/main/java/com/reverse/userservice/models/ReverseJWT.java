package com.reverse.userservice.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.UUID;

@Slf4j
@NoArgsConstructor
/**
 * Holds and handles a JWT token.
 */
public class ReverseJWT {

    @Getter
    private String token;

    private UUID uuid;

    /**
     * Generates the JWT token with object attributes.
     * @param userID The userId for the new token
     */
    public ReverseJWT(Long userID, String secret) {
        log.debug("Constructing new ReverseJWT");
        this.uuid = UUID.randomUUID();
        this.generateToken(userID, secret);
        log.debug("Finished constructing new ReverseJWT");
    }


    private void generateToken(Long userID, String secret) {
        log.debug("Generating new token with userID = "+userID);
        SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));

        int tokenLife = 3600;
        token = Jwts.builder().setSubject(String.valueOf(userID)).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + tokenLife * 1000)).signWith(key, SignatureAlgorithm.HS512)
                .setId(this.uuid.toString()).compact();
        log.debug("Token generated with userID = "+userID);
    }

    /**
     * Gets the userId from the JWT. Returns null if the signature is invalid.
     * @return The username from the JWT. Null if the JWT is invalid
     */
    @JsonIgnore
    public Long getUserID(String secret) {
        log.debug("Getting userID");
        try {
            Long userID = Long.parseLong(Jwts.parserBuilder().setSigningKey(secret).build().parseClaimsJws(token).getBody().getSubject());
            log.debug("Got userID = "+userID);
            return userID;
        } catch(SignatureException e) {
            log.warn("JWT invalid!");
        } catch(ExpiredJwtException e) {
            log.debug("JWT expired!");
        } catch(NumberFormatException e) {
            log.warn("JWTSubject NAN!");
        }
        return null;
    }
}
