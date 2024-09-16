package com.ar.gallerion.image;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
interface ImageRepository extends CrudRepository<ImageModel, Long> {
    @Query("SELECT imageTable FROM images imageTable WHERE imageTable.author=?1")
    List<ImageModel> getAllImagesByUsername(String username);
}
