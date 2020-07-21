package com.ovi.user.auth;

import ch.qos.logback.classic.pattern.MessageConverter;
import com.ovi.user.payload.response.ApiError;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomAuthEntryPoint implements AuthenticationEntryPoint {


    private ObjectMapper mapper;

    StringHttpMessageConverter messageConverter;

    public CustomAuthEntryPoint() {
        this.mapper = new ObjectMapper();
        this.messageConverter = new StringHttpMessageConverter();
    }

    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException {
        ApiError apiError = new ApiError(HttpStatus.UNAUTHORIZED);
        apiError.setMessage(e.getMessage());
        apiError.setDebugMessage(e.getMessage());

        ServerHttpResponse outputMessage = new ServletServerHttpResponse(httpServletResponse);
        outputMessage.setStatusCode(HttpStatus.UNAUTHORIZED);

        messageConverter.write(mapper.writeValueAsString(apiError), MediaType.APPLICATION_JSON, outputMessage);
    }
}
