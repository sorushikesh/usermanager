package com.bilsora.userManager.repository;

import com.bilsora.userManager.entity.Organization;
import com.bilsora.userManager.entity.User;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

  Optional<User> findByEmail(String email);

  List<User> findByOrganization(Organization organization);

  boolean existsByEmail(String email);
}
