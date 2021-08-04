package com.fita.project.controller;

import com.fita.project.dto.reponses.Response;
import com.google.api.client.http.AbstractInputStreamContent;
import com.google.api.client.http.InputStreamContent;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
@RestController
@RequestMapping(value = "/api/google/", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(value = "*", maxAge = -1)
public class GoogleController {
    @Autowired
    Drive googleDrive;

    @RequestMapping(value = "/get-list", method = RequestMethod.GET)
    private ResponseEntity<String> getAllGoogleDriveFiles() throws IOException {
        FileList result = googleDrive.files().list()
                .setFields("nextPageToken, files(id,webViewLink,name,parents,webContentLink,mimeType)")
                .execute();
        Response response = new Response();
        response.setMessage("Get list file success");
        response.setData(result.getFiles());
        return new ResponseEntity<>(getResponse(response), HttpStatus.OK);
    }

    @PostMapping(value = "/create")
    private ResponseEntity<String> createNewFolder(@RequestParam(name = "parent", required = false) String parent, @RequestParam(value = "name") String folderName) throws IOException {
        File fileMetadata = new File();
        fileMetadata.setName(folderName);
        fileMetadata.setMimeType("application/vnd.google-apps.folder");
        if (parent != null) {
            List<String> parents = Arrays.asList(parent);
            fileMetadata.setParents(parents);
        }
        File file = googleDrive.files().create(fileMetadata).setFields("id,name,webContentLink, webViewLink, parents").execute();
        Response response = new Response();
        response.setMessage("create folder success");
        response.setData(file);
        return new ResponseEntity<>(getResponse(response),HttpStatus.OK);
    }

    @PostMapping(value = "/delete")
    private ResponseEntity<String> deleteFile(@RequestParam(value = "fileId") String fileId) {
        String mess = "";
        int errorCode = 0;
        try {
            googleDrive.files().delete(fileId).execute();
            mess = "Delete file success";
        } catch (IOException e) {
            System.out.println("An error occurred: " + e);
            errorCode = 1;
            mess = "Error when delete file";
        }
        Response response = new Response();
        response.setErrorCode(errorCode);
        response.setMessage(mess);
        return new ResponseEntity<>(getResponse(response),HttpStatus.OK);
    }

    @PostMapping(value = "/upload")
    public ResponseEntity<String> uploadFile(@RequestParam(value = "parents") String parent, @RequestParam("file") MultipartFile files) throws IOException {
        File file = upLoadFile(parent, files);
        Response response = new Response();
        response.setMessage("Upload file success");
        response.setData(file);

        return new ResponseEntity<>(getResponse(response),HttpStatus.OK);
    }

    private File upLoadFile(String parent, MultipartFile files) throws IOException {
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

    @GetMapping(value = "download")
    public ResponseEntity<InputStreamResource> download(@RequestParam(value = "link") String link, @RequestParam("fileName") String name) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + name);
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");

        try {
            URL url = new URL(link);
            InputStream in = url.openStream();
            InputStreamResource inputStreamResource = new InputStreamResource(in);
            return new ResponseEntity<InputStreamResource>(inputStreamResource, headers, HttpStatus.OK);
        } catch (
                Exception ex) {
            return new ResponseEntity<InputStreamResource>(null, headers, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public String getResponse(Response response) {
        Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
        return gson.toJson(response);
    }

    @GetMapping(value = "searchFile")
    public ResponseEntity<String> searchFile(@RequestParam(value = "fileName") String name) throws IOException {
        String pageToken = null;
        List<File> list = new ArrayList<>();

        String query = " name contains '" + name + "' " //
                + " and mimeType != 'application/vnd.google-apps.folder' ";

        do {
            FileList result = googleDrive.files().list().setQ(query).setSpaces("drive") //
                    // Fields will be assigned values: id, name, createdTime, mimeType
                    .setFields("nextPageToken, files(id, name, createdTime, mimeType)")//
                    .setPageToken(pageToken).execute();
            for (File file : result.getFiles()) {
                list.add(file);
            }
            pageToken = result.getNextPageToken();
        } while (pageToken != null);

        Response response = new Response();
        response.setMessage("Search file success");
        response.setData(list);
        return new ResponseEntity<>(getResponse(response),HttpStatus.OK);
    }

    @GetMapping(value = "searchFolder")
    public ResponseEntity<String> searchFolder(@RequestParam(value = "parent", required = false) String parent, @RequestParam(value = "folderName") String name) throws IOException {
        String pageToken = null;
        List<File> list = new ArrayList<File>();

        String query = null;
        if (parent == null) {
            query = " name contains '" + name + "' "
                    + " and mimeType = 'application/vnd.google-apps.folder' "
            ;
        } else {
            query = " name contains '" + name + "' "
                    + " and mimeType = 'application/vnd.google-apps.folder' "
                    + " and '" + parent + "' in parents";
        }

        do {
            FileList result = googleDrive.files().list().setQ(query).setSpaces("drive")
                    .setFields("nextPageToken, files(id, name, createdTime,mimeType)")
                    .setPageToken(pageToken).execute();
            for (File file : result.getFiles()) {
                list.add(file);
            }
            pageToken = result.getNextPageToken();
        } while (pageToken != null);
        Response response = new Response();
        response.setMessage("Search folder success");
        response.setData(list);
        return new ResponseEntity<>(getResponse(response),HttpStatus.OK);
    }
}
