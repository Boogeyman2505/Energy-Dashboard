package com.ketan.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ketan.model.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
	
}