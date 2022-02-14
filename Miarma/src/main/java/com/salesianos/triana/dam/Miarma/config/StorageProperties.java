package com.salesianos.triana.dam.Miarma.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "storage")
@Getter @Setter
@Configuration
public class StorageProperties {

    private String location;
}
