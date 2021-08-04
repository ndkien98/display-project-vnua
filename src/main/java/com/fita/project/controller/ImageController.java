package com.fita.project.controller;

import com.fita.project.dto.reponses.BaseResponse;
import com.fita.project.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value = "/api/image", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ImageController {


    @Autowired
    ImageService imageService;

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public ResponseEntity<BaseResponse> updateImg(@RequestBody MultipartFile file) {
        BaseResponse baseResponse = new BaseResponse();
        try {
            baseResponse.setData(this.imageService.uploadImg(file,"Test"));
            baseResponse.setErrorCode(200);
            baseResponse.setMessage("upload img success");
            return ResponseEntity.ok(baseResponse);
        } catch (IOException e) {
            e.printStackTrace();
            baseResponse.setErrorCode(-1);
            baseResponse.setMessage("error");
            return ResponseEntity.ok(baseResponse);
        }
    }
}
