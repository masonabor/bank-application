package com.edu.bankapplication.web.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

@Configuration
public class SecurityKeyConfig {

    @Value("classpath:keys/private.pem")
    private Resource privateKeyResource;

    @Value("classpath:keys/public.pem")
    private Resource publicKeyResource;

    @Bean
    public RSAPublicKey rsaPublicKey() throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        String key = Files.readString(
                Paths.get(publicKeyResource.getURI()));

        key = key
                .replace("-----BEGIN PUBLIC KEY-----", "")
                .replace("-----END PUBLIC KEY-----", "")
                .replaceAll("\\s", "")
                .trim();

        byte[] decoded = Base64.getDecoder().decode(key);

        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(decoded);

        KeyFactory keyFactory = KeyFactory.getInstance("RSA");

        return (RSAPublicKey) keyFactory.generatePublic(keySpec);
    }

    @Bean
    public RSAPrivateKey rsaPrivateKey() throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        String key = Files.readString(
                Paths.get(privateKeyResource.getURI()));

        key = key
                .replace("-----BEGIN PRIVATE KEY-----", "")
                .replace("-----END PRIVATE KEY-----", "")
                .replaceAll("\\s", "")
                .trim();

        byte[] decoded = Base64.getDecoder().decode(key);

        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(decoded);

        KeyFactory keyFactory = KeyFactory.getInstance("RSA");

        return (RSAPrivateKey) keyFactory.generatePrivate(keySpec);
    }
}
