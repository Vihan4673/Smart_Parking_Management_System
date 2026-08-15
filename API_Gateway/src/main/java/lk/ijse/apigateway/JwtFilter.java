package lk.ijse.apigateway;


import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import lk.ijse.apigateway.util.JwtUtil;

import java.util.List;
import java.util.Map;

@Component
public class JwtFilter implements GlobalFilter {

    @Autowired
    private JwtUtil jwtUtil;

    private static final String AUTHORIZATION = "Authorization";
    private static final String BEARER = "Bearer ";

    private static final Map<String, List<String>> roleAccessMap = Map.of(
            "/admin", List.of("ADMIN"),
            "/owner", List.of("OWNER"),
            "/user", List.of("USER", "DRIVER"),
            "/vehicles/user", List.of("USER", "DRIVER"),
            "/payments/user", List.of("USER", "DRIVER"),
            "/reservations/user", List.of("USER", "DRIVER"),
            "/public", List.of("ANY") // Optional: allow public APIs
    );

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String path = exchange.getRequest().getURI().getPath();

        // Skip login/register endpoints
        if (path.contains("/user_service/api/auth/login") || path.contains("/user_service/api/auth/register")) {
            return chain.filter(exchange);
        }

        String authHeader = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }

        String token = authHeader.substring(7); // remove "Bearer "

        if (!jwtUtil.isTokenValid(token)) {
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }

        // Extract user info
        String email = jwtUtil.getUsername(token);
        String role = jwtUtil.getUserRole(token);

        System.out.println(email + " " + role);

        // Add headers to pass to downstream services
        ServerHttpRequest modifiedRequest = exchange.getRequest()
                .mutate()
                .header("X-User-Email", email)
                .header("X-User-Role", role)
                .build();

        return chain.filter(exchange.mutate().request(modifiedRequest).build());
    }


    private Mono<Void> onError(ServerWebExchange exchange, String err, HttpStatus status) {
        exchange.getResponse().setStatusCode(status);
        return exchange.getResponse().setComplete();
    }
}