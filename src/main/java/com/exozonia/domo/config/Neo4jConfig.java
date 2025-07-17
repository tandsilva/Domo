package com.exozonia.domo.config;

import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.config.AbstractNeo4jConfig;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;

@Configuration
//@EnableNeo4jRepositories(basePackages = "com.exozonia.domo.repository")  // <-- aqui, pacote correto
public class Neo4jConfig extends AbstractNeo4jConfig {

    @Value("${spring.neo4j.uri}")
    private String uri;

    @Value("${spring.neo4j.authentication.username}")
    private String username;

    @Value("${spring.neo4j.authentication.password}")
    private String password;

    @Bean
    @Override
    public Driver driver() {
        return GraphDatabase.driver(uri, AuthTokens.basic(username, password));
    }
}
