package org.ecommerce.app.log;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.UUID;


@Component
@Slf4j
public class HttpLogger extends OncePerRequestFilter {



    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        final String MDC_UUID_TOKEN_KEY = "txID";

        boolean dontLog = request.getServletPath().equalsIgnoreCase("/")
                || request.getServletPath().contains("swagger")
                || request.getServletPath().contains("actuator")
                || request.getServletPath()
                .equalsIgnoreCase("/v2/api-docs");

        if (dontLog) {
            filterChain.doFilter(request, response);
            return;
        }

        if ("OPTIONS".equals(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            long startTime = System.currentTimeMillis();

            try {
                MDC.put(MDC_UUID_TOKEN_KEY, UUID.randomUUID().toString());
                filterChain.doFilter(request, response);
            } catch (Exception ex) {
                log.error("Exception occurred in filter while setting UUID for logs", ex);
            } finally {
                log.info("path = [{}], response time = [{}] ms ", request.getRequestURI(), System.currentTimeMillis() - startTime);
                MDC.remove(MDC_UUID_TOKEN_KEY);
            }

        }
    }

}

