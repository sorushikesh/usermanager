package com.bilsora.userManager.config;

import com.bilsora.userManager.entity.TenantConfig;
import com.bilsora.userManager.repository.TenantConfigRepository;
import jakarta.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSourceConfig {

  private final Map<Object, Object> tenantDataSources = new HashMap<>();
  @Autowired
  private TenantConfigRepository tenantConfigRepository;

  @PostConstruct
  public void loadTenantDataSources() {

    List<TenantConfig> tenantConfigs = tenantConfigRepository.findAll();

    for (TenantConfig config : tenantConfigs) {
      DataSource dataSource = createDataSource(config);
      tenantDataSources.put(config.getTenantId(), dataSource);
    }
  }

  @Bean
  public DataSource dataSource() {
    TenantAwareRoutingDataSource routingDataSource = new TenantAwareRoutingDataSource();
    routingDataSource.setTargetDataSources(tenantDataSources);
    routingDataSource.setDefaultTargetDataSource(tenantDataSources.values().iterator().next());
    routingDataSource.afterPropertiesSet();
    return routingDataSource;
  }

  private DataSource createDataSource(TenantConfig config) {
    DataSourceProperties properties = new DataSourceProperties();
    properties.setUrl(config.getUrl());
    properties.setUsername(config.getUsername());
    properties.setPassword(config.getPassword());
    return properties.initializeDataSourceBuilder().build();
  }
}
