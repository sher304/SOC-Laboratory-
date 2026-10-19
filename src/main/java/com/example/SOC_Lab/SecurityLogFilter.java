package com.example.SOC_Lab;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class SecurityLogFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger("SecurityLogger");

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String ipAddress = httpRequest.getRemoteAddr();
        String method = httpRequest.getMethod();
        String uri = httpRequest.getRequestURI();
        String queryString = httpRequest.getQueryString();

        String fullUrl = uri + (queryString != null ? "?" + queryString : "");
        String safeUrl = fullUrl.replace("\"",  "\\\"");

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);

        String jsonLog = String.format(
                "{\"timestamp\": \"%s\", \"ip\": \"%s\", \"method\": \"%s\", \"url\": \"%s\"}",
                timestamp, ipAddress, method, safeUrl
        );

        logger.warn(jsonLog);
        chain.doFilter(request, response);
    }
}