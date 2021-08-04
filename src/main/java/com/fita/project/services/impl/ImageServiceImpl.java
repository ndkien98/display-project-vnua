package com.fita.project.services.impl;

import com.fita.project.dto.response.ImageResponse;
import com.fita.project.services.ImageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class ImageServiceImpl implements ImageService {

    @Value("${path.image}")
    private String path;

    @Override
    public ImageResponse uploadImg(MultipartFile file,String nameFolder) throws IOException {

        String partDirByDate = new File("").getAbsolutePath() + path + "/" + nameFolder;
        File fileCreateDir = new File(partDirByDate);
        if (!fileCreateDir.exists()) {
            fileCreateDir.mkdirs();
        }
        String fileName = file.getOriginalFilename();
        String imagepath = partDirByDate + "/" + fileName;
        Path path = Paths.get(imagepath);
        byte[] bytes = file.getBytes();
        Files.write(path, bytes);

        ImageResponse imageResponse = ImageResponse.builder().imageLink(imagepath).imageName(file.getOriginalFilename()).imageSize(file.getSize()).build();
        return imageResponse;
    }

}
