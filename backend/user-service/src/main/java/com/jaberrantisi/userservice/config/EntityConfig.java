package com.jaberrantisi.userservice.config;

import com.speedment.jpastreamer.application.JPAStreamer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EntityConfig {

    private final EntityManagerFactory entityManagerFactory;

    @Autowired
    public EntityConfig(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Bean
    public JPAStreamer jpaStreamer() {
        return JPAStreamer.of(entityManagerFactory);
    }


}
