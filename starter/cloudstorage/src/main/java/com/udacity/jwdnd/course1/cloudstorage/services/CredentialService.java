package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mappers.CredentialsMapper;
import com.udacity.jwdnd.course1.cloudstorage.models.Credential;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CredentialService {
    private final CredentialsMapper credentialsMapper;
    private final EncryptionService encryptionService;
    public CredentialService(CredentialsMapper credentialsMapper, EncryptionService encryptionService) {
        this.credentialsMapper = credentialsMapper;
        this.encryptionService = encryptionService;
    }

    public Credential getCredential(Integer credentialId){
        return this.credentialsMapper.getCredential(credentialId);
    }


    public List<Credential> getAllCredentials(Integer userId){
        return this.credentialsMapper.getAllCredentials(userId);
    }

    public void insertCredential(Credential credential){
        credential.setPassword(encryptionService.encryptValue(credential.getPassword(), credential.getKey()));
        this.credentialsMapper.insertCredential(credential);
    }

    public void updateCredential(Credential credential){
        credential.setPassword(encryptionService.encryptValue(credential.getPassword(), credential.getKey()));
        this.credentialsMapper.updateCredential(credential.getUrl(), credential.getUsername(), credential.getKey(), credential.getPassword(), credential.getUserId(), credential.getCredentialId());
    }

    public void deleteCredential(Integer credentialId){
        this.credentialsMapper.deleteCredential(credentialId);
    }
}
