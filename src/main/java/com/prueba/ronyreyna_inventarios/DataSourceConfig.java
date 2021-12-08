package com.prueba.ronyreyna_inventarios;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.net.InetAddress;
import java.net.UnknownHostException;

@Configuration
public class DataSourceConfig {

    @Bean
    public DataSource getDataSource() throws UnknownHostException {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("com.mysql.cj.jdbc.Driver");
        dataSourceBuilder.url("jdbc:mysql://localhost:3306/rony_inventario");
        dataSourceBuilder.username("root");
        dataSourceBuilder.password("12345");
        return dataSourceBuilder.build();
    }
}