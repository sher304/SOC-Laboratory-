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

@Component
public class SecurityLogFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger("SecurityLogger");

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String ipAddress = httpRequest.getRemoteAddr();
        String uri = httpRequest.getRequestURI();
        String queryString = httpRequest.getQueryString();

        String fullUrl = uri + (queryString != null ? "?" + queryString : "");

        logger.warn("SOC-Alert! IP: {} is accessing URL: {}", ipAddress, fullUrl);

        chain.doFilter(request, response);
    }
}