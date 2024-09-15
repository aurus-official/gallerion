package com.ar.gallerion.image;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;
import java.util.List;

@Service
class ImageService {

    private static String CURRENT_WORKING_DIRECTORY;
    private ImageRepository imageRepository;

    @Autowired
    ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    List<ImageModel> getAllImages() {
        return StreamSupport
                .stream(imageRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    boolean isSuccessfulUploadImages(MultipartFile[] images, String username) throws IOException {
        this.setupCurrentWorkingDirectory();

        String usernameImagesDirectory = CURRENT_WORKING_DIRECTORY.concat(username);

        if (!Files.isDirectory(Paths.get(usernameImagesDirectory))) {
            Files.createDirectory(Paths.get(usernameImagesDirectory));
        }
        System.out.println(String.format("%s DIRECTORY ALREADY EXISTS!", username));

        // for (MultipartFile image : images) {
        // Path filePath = Files
        // .createFile(Paths
        // .get(usernameImagesDirectory.concat(String.format("/%s",
        // image.getOriginalFilename()))));
        // image.transferTo(filePath);
        // }

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

                System.out.println(Files.exists(createdFilePath));

                if (Files.exists(createdFilePath)) {
                    return;
                }

                image.transferTo(createdFilePath);

            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        return true;
    }

    private void setupCurrentWorkingDirectory() {
        String cwdUnparsed = Paths.get("").toAbsolutePath().toString();
        CURRENT_WORKING_DIRECTORY = cwdUnparsed.substring(0, cwdUnparsed.lastIndexOf("/") + 1).concat("imagefs/");
    }
}
