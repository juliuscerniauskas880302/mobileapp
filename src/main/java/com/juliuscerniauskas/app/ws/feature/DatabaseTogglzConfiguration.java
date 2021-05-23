package com.juliuscerniauskas.app.ws.feature;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.togglz.core.Feature;
import org.togglz.core.manager.TogglzConfig;
import org.togglz.core.repository.StateRepository;
import org.togglz.core.repository.jdbc.JDBCStateRepository;
import org.togglz.core.repository.util.DefaultMapSerializer;
import org.togglz.core.user.NoOpUserProvider;
import org.togglz.core.user.UserProvider;

import javax.sql.DataSource;

@Component
public class DatabaseTogglzConfiguration implements TogglzConfig {
    @Value("${spring.datasource.driver-class-name}")
    private String driverClass;
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String password;
    @Value("${spring.datasource.url}")
    private String dataSource;
    @Value("${features.toggle.database.table}")
    private String tableName;

    @Override
    public Class<? extends Feature> getFeatureClass() {
        return VcatchFeatures.class;
    }

    @Override
    @Bean
    public StateRepository getStateRepository() {
        return JDBCStateRepository.newBuilder(getDataSource())
                .tableName(tableName)
                .createTable(true)
                .serializer(DefaultMapSerializer.singleline())
                .noCommit(true)
                .build();
    }

    @Override
    @Bean
    public UserProvider getUserProvider() {
        return new NoOpUserProvider();

    }

    private DataSource getDataSource() {
        DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName(driverClass);
        dataSourceBuilder.url(dataSource);
        dataSourceBuilder.username(username);
        dataSourceBuilder.password(password);
        return dataSourceBuilder.build();
    }
}
