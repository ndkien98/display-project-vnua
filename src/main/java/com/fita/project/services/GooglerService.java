package com.fita.project.services;

import com.google.api.services.drive.model.File;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public interface GooglerService {

    File updateFile(String parent, MultipartFile files) throws IOException;

}
