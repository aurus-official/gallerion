package com.ar.gallerion.config;

import javax.sql.DataSource;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatabaseConfig {

    @Bean
    public DataSource dataSource() {
        DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("org.mariadb.jdbc.Driver");
        dataSourceBuilder.url("jdbc:mariadb://localhost:3306/gallerion");
        dataSourceBuilder.username("aurus");
        dataSourceBuilder.password("june232004");

        return dataSourceBuilder.build();
    }
}
