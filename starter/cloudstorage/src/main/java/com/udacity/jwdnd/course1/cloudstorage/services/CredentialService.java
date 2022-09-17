package com.udacity.jwdnd.course1.cloudstorage.services;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;

import org.springframework.stereotype.Service;

import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credential;



@Service
public class CredentialService {

    private final EncryptionService encryptor;
    private final CredentialMapper credentials;

    public CredentialService(CredentialMapper mapper, EncryptionService encryptionService) {
        this.credentials = mapper;
        encryptor = encryptionService;
    }

    public String decryptCredential(Credential credential) {
        Credential found = credentials.find(credential);
        return encryptor.decryptValue(found.getPassword(), found.getUserkey());
    }

    public List<Credential> allBy(Integer userId) {
        return credentials.allFrom(userId);
    }

    public void remove(Credential credential) {
        credentials.delete(credential);
    }

    public void add(Credential credential) {
        if (credential.getCredentialId() == null) {
            String key = generateKey();
            String encryptedPassword = encryptor.encryptValue(credential.getPassword(), key);

            credentials.insert(
                new Credential(
                    null,
                    credential.getUrl(),
                    key,
                    credential.getUserId(),
                    credential.getUsername(),
                    encryptedPassword
                    
                )
            );

            return;
        }

        String key = credentials.find(credential).getUserkey();
        String rawPassword = encryptor.decryptValue(credential.getPassword(), key) ;
        credential.setPassword(encryptor.encryptValue(rawPassword, key));

        credentials.update(credential);
    }

    private String generateKey() {
        try {
            SecureRandom random = new SecureRandom();
            byte[] key = new byte[16];
            random.nextBytes(key);
            return Base64.getEncoder().encodeToString(key);

        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return null;
    }

}
