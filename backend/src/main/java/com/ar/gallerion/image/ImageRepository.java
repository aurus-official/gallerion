package com.ar.gallerion.image;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
interface ImageRepository extends CrudRepository<ImageModel, Long> {
}
