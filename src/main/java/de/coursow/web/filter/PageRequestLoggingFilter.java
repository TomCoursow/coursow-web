package de.coursow.web.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
@Order(1)
public class PageRequestLoggingFilter extends OncePerRequestFilter {

    private static final Logger log = LoggerFactory.getLogger(PageRequestLoggingFilter.class);

    private static final List<String> EXCLUDES = List.of("/favicon.ico", "/css/", "/robots.txt");

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain chain) throws ServletException, IOException {
        log.info("IN: {} {}", request.getMethod(), request.getRequestURI());

        long startTime = System.currentTimeMillis();
        chain.doFilter(request, response);
        long duration = System.currentTimeMillis() - startTime;

        String redirectLocation = response.getHeader("location");
        if (redirectLocation == null) {
            log.info("OUT: {} {} -> {} ({} ms)", request.getMethod(), request.getRequestURI(), response.getStatus(), duration);
        } else {
            log.info("OUT: {} {} -> {} {} ({} ms)", request.getMethod(), request.getRequestURI(), response.getStatus(), redirectLocation, duration);
        }
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return EXCLUDES.stream().anyMatch(request.getRequestURI()::startsWith);
    }
}
