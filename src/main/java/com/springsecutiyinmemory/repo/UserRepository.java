package com.springsecutiyinmemory.repo;

import com.springsecutiyinmemory.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {

}
