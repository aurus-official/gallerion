package com.ar.gallerion.user;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

interface UserRepository extends CrudRepository<UserModel, Long> {
    @Query("SELECT utable FROM users utable WHERE utable.username=?1")
    UserModel findByUsername(String username);

    @Query("SELECT IF(EXISTS(SELECT utable FROM users utable WHERE utable.username=?1), 1, 0)")
    int isUsernameExists(String username);
}
