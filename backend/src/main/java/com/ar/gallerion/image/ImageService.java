package com.ar.gallerion.image;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ar.gallerion.exception.IllegalResourceAccessException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.io.IOException;
import java.util.List;

@Service
class ImageService {

    private static String CURRENT_WORKING_DIRECTORY;
    private ImageRepository imageRepository;

    @Autowired
    ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
        this.setupCurrentWorkingDirectory();
    }

    List<ImageModel> getAllImages(String username, String authenticatedUser) {

        if (!validateAuthenticatedUser(username, authenticatedUser)) {
            throw new IllegalResourceAccessException("RESOURCES NOT ALLOWED TO ACCESS!");
        }

        List<ImageModel> images = StreamSupport
                .stream(imageRepository.getAllImagesByUsername(username).spliterator(), false)
                .collect(Collectors.toList());

        images.forEach(image -> {
            try {
                image.setImageBytes(
                        Files.readAllBytes(Paths.get(CURRENT_WORKING_DIRECTORY.concat(image.getFileUrl()))));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        return images;
    }

    boolean isSuccessfulUploadImages(MultipartFile[] images, String username, String authenticatedUser)
            throws IOException {

        if (!validateAuthenticatedUser(username, authenticatedUser)) {
            throw new IllegalResourceAccessException("RESOURCES NOT ALLOWED TO ACCESS!");
        }

        String usernameImagesDirectory = CURRENT_WORKING_DIRECTORY.concat(username);

        if (!Files.isDirectory(Paths.get(usernameImagesDirectory))) {
            Files.createDirectory(Paths.get(usernameImagesDirectory));
        }

        Stream.of(images).forEach(image -> {
            try {

                Path tempFilePath = Paths
                        .get(usernameImagesDirectory
                                .concat(String.format("/%s", image.getOriginalFilename())));

                if (Files.exists(tempFilePath)) {
                    return;
                }

                Path createdFilePath = Files
                        .createFile(Paths
                                .get(usernameImagesDirectory
                                        .concat(String.format("/%s", image.getOriginalFilename()))));

                image.transferTo(createdFilePath);

                ImageModel tempModel = new ImageModel();
                tempModel.setName(image.getOriginalFilename());
                tempModel.setFileUrl(String.format("%s/%s", username, image.getOriginalFilename()));
                tempModel.setTimestamp(LocalDateTime.now());
                tempModel.setAuthor(username);

                imageRepository.save(tempModel);

            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        return true;
    }

    private boolean validateAuthenticatedUser(String username, String authenticatedUsername) {
        if (username.compareTo(authenticatedUsername) != 0) {
            System.out.println("USERNAME IS NOT MATCHED WITH AUTHENTICATED USER!");
            return false;
        }

        return true;
    }

    private void setupCurrentWorkingDirectory() {
        String cwdUnparsed = Paths.get("").toAbsolutePath().toString();
        CURRENT_WORKING_DIRECTORY = cwdUnparsed.substring(0, cwdUnparsed.lastIndexOf("/") + 1).concat("imagefs/");
    }
}
