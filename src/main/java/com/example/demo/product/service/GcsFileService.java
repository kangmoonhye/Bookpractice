package com.example.demo.product.service;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.UUID;

@Service
public class GcsFileService {

    @Value("${gcp.storage.bucket}")
    private String bucketName;

    @Value("${gcp.storage.credentials}")
    private String credentialsPath;

    private Storage storage;

    @PostConstruct
    public void init() throws IOException {
        GoogleCredentials credentials =
                GoogleCredentials.fromStream(new FileInputStream(credentialsPath));

        storage = StorageOptions.newBuilder()
                .setCredentials(credentials)
                .build()
                .getService();
    }

    public String upload(MultipartFile file) throws IOException {
        if (file == null || file.isEmpty()) {
            return null;
        }

        String filename = UUID.randomUUID() + "_" + file.getOriginalFilename();

        BlobInfo blobInfo = BlobInfo.newBuilder(bucketName, filename)
                .setContentType(file.getContentType())
                .build();

        storage.create(blobInfo, file.getBytes());

        return "https://storage.googleapis.com/" + bucketName + "/" + filename;
    }
}