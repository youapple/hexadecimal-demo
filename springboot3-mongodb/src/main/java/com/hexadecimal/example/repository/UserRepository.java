package com.hexadecimal.example.repository;

import com.hexadecimal.example.model.UserDO;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<UserDO, Long> {

}
