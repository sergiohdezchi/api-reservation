package com.helier.api_reservations.connector;

import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.helier.api_reservations.connector.configuration.EndpointConfiguration;
import com.helier.api_reservations.connector.configuration.HostConfiguration;
import com.helier.api_reservations.connector.configuration.HttpConectorConfiguration;
import com.helier.api_reservations.connector.response.CityDTO;
import com.helier.api_reservations.exceotion.ApiException;

import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import reactor.netty.http.client.HttpClient;

@Component
public class CatalogConnector {
    private static final org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger(CatalogConnector.class);
    private final String HOST = "api-catalog";
    private final String ENDPOINT = "get-city";
    private HttpConectorConfiguration httpConectorConfiguration;

    public CatalogConnector(HttpConectorConfiguration httpConectorConfiguration) {
        this.httpConectorConfiguration = httpConectorConfiguration;
    }

    @CircuitBreaker(name = "api-catalog", fallbackMethod = "getCityFallback")
    public CityDTO getCity(String code) {

        LOGGER.info("calling to api-catalog");

        HostConfiguration host = httpConectorConfiguration.getHosts().get(HOST);
        EndpointConfiguration endpoint = host.getEndpoints().get(ENDPOINT);

        HttpClient httpClient = HttpClient.create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, Math.toIntExact(endpoint.getConnectionTimeout()))
                .doOnConnected(conn -> conn.addHandlerLast(new ReadTimeoutHandler(endpoint.getReadTimeout()))
                        .addHandlerLast(new WriteTimeoutHandler(endpoint.getWriteTimeout())));

        WebClient webClient = WebClient.builder().baseUrl("https://" + host.getHost() + endpoint.getUrl())
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .clientConnector(new ReactorClientHttpConnector(httpClient)).build();

        return webClient.get().uri(uriBuilder -> uriBuilder.build(code)).retrieve().bodyToFlux(CityDTO.class).share()
                .blockFirst();
    }

    public CityDTO getCityFallback(String code, CallNotPermittedException ex) {
        LOGGER.error("Circuit breaker activated for getCity: {}", ex.getMessage());
        throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR, "Circuit breaker activated for getCity",
                List.of(ex.getMessage()));
    }

    public CityDTO getCityFallback(String code, Exception ex) {
        LOGGER.error("Fallback for getCity when circuit breaker is not activated: {}", ex.getMessage());
        throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR,
                "Fallback for getCity when circuit breaker is not activated", List.of(ex.getMessage()));
    }
}
