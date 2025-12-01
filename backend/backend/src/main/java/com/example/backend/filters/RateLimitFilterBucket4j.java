package com.example.backend.filters;

 import io.github.bucket4j.*;
 import org.springframework.stereotype.Component;
 import org.springframework.web.filter.OncePerRequestFilter;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class RateLimitFilterBucket4j extends OncePerRequestFilter {

    private final Map<String, Bucket> cache = new ConcurrentHashMap<>();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                 FilterChain filterChain) throws ServletException, IOException {

        String key = request.getRemoteAddr();
        Bucket bucket = cache.computeIfAbsent(key, this::newBucket);

        if (bucket.tryConsume(1)) {
            filterChain.doFilter(request, response);
        } else {
            response.setStatus(429);
            response.getWriter().write("Limite de requisições atingido. Tente novamente mais tarde.");
        }
    }

    private Bucket newBucket(String key) {
        Refill refill = Refill.greedy(10, Duration.ofSeconds(30)); // 10 req a cada 30s
        Bandwidth limit = Bandwidth.classic(10, refill);
        return Bucket.builder().addLimit(limit).build();
    }
}