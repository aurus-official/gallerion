package com.ar.gallerion.image;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpServletRequest;

@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
@RequestMapping("/v1/images")
@RestController
class ImageController {

    private ImageService imageService;

    @Autowired
    ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @GetMapping(path = "/{username}")
    ResponseEntity<List<ImageModel>> getImageOnUsername(@PathVariable(name = "username") String username,
            Authentication authentication, HttpServletRequest httpServletRequest) {
        List<ImageModel> files = imageService.getAllImages(username, authentication.getName());
        System.out.println(httpServletRequest.getCookies());
        return new ResponseEntity<List<ImageModel>>(files, HttpStatus.OK);
    }

    @PostMapping(path = "/{username}")
    ResponseEntity<String> uploadFilesOnUsername(@PathVariable(name = "username") String username,
            @RequestParam MultipartFile[] images, Authentication authentication) throws MultipartException {
        try {
            imageService.isSuccessfulUploadImages(images, username, authentication.getName());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok().body("IT WORKED, PROBABLY!");
    }
}
