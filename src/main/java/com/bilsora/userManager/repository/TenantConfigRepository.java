package com.bilsora.userManager.repository;

import com.bilsora.userManager.entity.TenantConfig;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TenantConfigRepository extends JpaRepository<TenantConfig, String> {
}
