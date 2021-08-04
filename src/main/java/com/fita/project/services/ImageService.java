package com.fita.project.services;

import com.fita.project.dto.response.ImageResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public interface ImageService {

    ImageResponse uploadImg(MultipartFile file, String nameFolder) throws IOException;

}
