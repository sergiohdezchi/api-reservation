package com.helier.api_reservations.connector.configuration;

import java.util.HashMap;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "http-connector")
public class HttpConectorConfiguration {
    private HashMap<String, HostConfiguration> hosts;

    public HashMap<String, HostConfiguration> getHosts() {
        return hosts;
    }

    public void setHosts(HashMap<String, HostConfiguration> hosts) {
        this.hosts = hosts;
    }
}
