package com.bilsora.usermanager.repository;

import com.bilsora.usermanager.model.Users;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Users, String> {

  Optional<Users> findByUsername(String username);
}
