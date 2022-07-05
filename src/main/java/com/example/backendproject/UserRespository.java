package com.example.backendproject;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

@Repository
public interface UserRespository extends CrudRepository<UserEntity, Long> {

}
