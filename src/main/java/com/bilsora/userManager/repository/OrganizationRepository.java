package com.bilsora.userManager.repository;

import com.bilsora.userManager.entity.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface OrganizationRepository extends JpaRepository<Organization, UUID> {

  Optional<Organization> findByName(String name);

  boolean existsByName(String name);
}
