package dev.branchesandgomes.service;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import dev.branchesandgomes.model.entity.CustomerEntity;
import dev.branchesandgomes.model.entity.SellerEntity;
import dev.branchesandgomes.model.enums.RoleType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
public class JwtService {
    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.expiration}")
    private Long expirationSeconds;
    private final String issuer = "ecommerce-backend";


    public String generateToken(CustomerEntity customer) {
        Algorithm algorithm = Algorithm.HMAC256(secret);

        return JWT.create()
                .withIssuer(issuer)
                .withIssuedAt(creationDate())
                .withExpiresAt(expirationDate())
                .withSubject(customer.getExternalId().toString())
                .withClaim("type", "CUSTOMER")
                .sign(algorithm);
    }

    public String generateToken(SellerEntity seller) {
        Algorithm algorithm = Algorithm.HMAC256(secret);

        return JWT.create()
                .withIssuer(issuer)
                .withIssuedAt(creationDate())
                .withExpiresAt(expirationDate())
                .withSubject(seller.getExternalId().toString())
                .withClaim("type", "SELLER")
                .sign(algorithm);
    }

    public UUID getUserExternalIdFromToken(String token) {
        Algorithm algorithm = Algorithm.HMAC256(secret);

        String subject = JWT.require(algorithm)
                .withIssuer(issuer)
                .build()
                .verify(token)
                .getSubject();

        return UUID.fromString(subject);
    }

    public RoleType getUserTypeFromToken(String token) {
        Algorithm algorithm = Algorithm.HMAC256(secret);

        String type = JWT.require(algorithm)
                .withIssuer(issuer)
                .build()
                .verify(token)
                .getClaim("type")
                .asString();

        return RoleType.valueOf(type);
    }

    private Instant creationDate() {
        return Instant.now();
    }

    private Instant expirationDate() {
        return Instant.now().plusSeconds(expirationSeconds);
    }
}
