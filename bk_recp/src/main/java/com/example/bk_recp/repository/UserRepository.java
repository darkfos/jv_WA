package com.example.bk_recp.repository;

import com.example.bk_recp.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
