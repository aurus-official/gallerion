package com.ar.gallerion.image;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin
@RequestMapping("/v1/images")
@RestController
class ImageController {

    private ImageService imageService;

    @Autowired
    ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @GetMapping(path = "/test")
    ResponseEntity<String> testing() {
        return ResponseEntity.ok().body("TESTING");
    }

    @GetMapping(path = "/{username}")
    ResponseEntity<List<ImageModel>> getFilesOnUsername(@PathVariable(name = "username") String username) {
        List<ImageModel> files = imageService.getAllImages();
        return ResponseEntity.ok().body(files);
    }

    @PostMapping(path = "/{username}")
    ResponseEntity<String> uploadFilesOnUsername(@PathVariable(name = "username") String username,
            @RequestParam MultipartFile[] images) throws MultipartException {
        try {
            imageService.isSuccessfulUploadImages(images, username);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return ResponseEntity.ok().body("IT WORKED, PROBABLY!");
    }
}
