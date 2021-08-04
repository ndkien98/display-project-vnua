package com.fita.project.services.impl;

import com.fita.project.services.GooglerService;
import com.google.api.client.http.AbstractInputStreamContent;
import com.google.api.client.http.InputStreamContent;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

@Service
public class GoogleServiceImpl implements GooglerService {

    @Autowired
    Drive googleDrive;

    @Override
    public File updateFile(String parent, MultipartFile files) throws IOException {
        InputStream inputStream = new BufferedInputStream(files.getInputStream());
        String fileName = files.getOriginalFilename();
        AbstractInputStreamContent uploadStreamContent = new InputStreamContent(files.getContentType(), inputStream);
        File fileMetadata = new File();
        fileMetadata.setName(fileName);
        List<String> parents = Arrays.asList(parent);
        fileMetadata.setParents(parents);

        File file = googleDrive.files().create(fileMetadata, uploadStreamContent)
                .setFields("id, name,webContentLink, webViewLink, parents").execute();
        return file;
    }
}
