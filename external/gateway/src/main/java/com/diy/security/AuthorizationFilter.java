package com.diy.security;

import lombok.extern.log4j.Log4j2;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Objects;

@Log4j2
@Component
public class AuthorizationFilter extends AbstractGatewayFilterFactory<AuthorizationFilter.Config> {

    private final String uri = "http://localhost:8000/auth/api/auth/validate";
    private final RestTemplate restTemplate = new RestTemplate();

    public AuthorizationFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        JwtChecks checks = new JwtChecks();

        return (exchange, chain) -> {

            ArrayList<String> adminURI = new ArrayList<>();
            ArrayList<String> userURI = new ArrayList<>();

            adminURI.add("http://localhost:8080/api/authentication/login");
            userURI.add("http://localhost:8080/api/authentication/login");

            String APITargeted = String.valueOf(exchange.getRequest().getURI());

            checks.setToken(exchange.getRequest().getHeaders().getFirst("Authorization"));
            String role = "authNeeded";
            if (checks.getToken() != null) {
                role = restTemplate.postForObject(uri, checks.getToken(), String.class);
            }

            if (Objects.equals(role, "authNeeded")) {
//                throw new RuntimeException("Authentication needed");
                return chain.filter(exchange);

            }

            if (Objects.equals(role, "SUPERADMIN")) {
                return chain.filter(exchange);
            } else if (Objects.equals(role, "ADMIN")) {
                if (adminURI.contains(APITargeted) || userURI.contains(APITargeted)) {
                    return chain.filter(exchange);
                } else {
                    throw new RuntimeException();
                }
            } else if (Objects.equals(role, "USER")) {
                if (userURI.contains(APITargeted)) {
                    return chain.filter(exchange);
                }
            } else {
                return chain.filter(exchange);
            }
            return chain.filter(exchange);
        };

    }

    public static class Config {
        private String baseMessage;
        private boolean preLogger;
        private boolean postLogger;
    }
}

