package com.bank.financial_product.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebExceptionHandler;
import reactor.core.publisher.Mono;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

@Component
@Order(-2)
public class GlobalExceptionHandler implements WebExceptionHandler {
    @Override
    public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
        if (ex instanceof HttpException) {
            try {
                return handleException(exchange, (HttpException) ex);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }
        return Mono.error(ex);
    }
    private Mono<Void> handleException(ServerWebExchange exchange, HttpException ex) throws JsonProcessingException {
        exchange.getResponse().setStatusCode(ex.getStatus());
        exchange.getResponse().getHeaders().setContentType(MediaType.APPLICATION_JSON);

        String defaultMessage = "message: " + ex.getStatus().value() + " " + ex.getStatus().name() + " " + "from " +
                exchange.getRequest().getMethod() +
                exchange.getRequest().getPath().value();

        ObjectMapper objectMapper = new ObjectMapper();
        String response = objectMapper.writeValueAsString(ErrorResponse.builder()
                        .message(StringUtils.hasText(ex.getMessage()) ? ex.getMessage() : defaultMessage)
                        .path(exchange.getRequest().getPath().value())
                        .errors(ex.getErrors())
                        .trace(Arrays.toString(ex.getStackTrace()))
                        .timestamp( ZonedDateTime.now( ZoneOffset.UTC ).format( DateTimeFormatter.ISO_INSTANT ))
                        .status(ex.getStatus().value())
                .build());
        return exchange.getResponse().writeWith(Mono.just(exchange.getResponse()
                .bufferFactory()
                .wrap(response.getBytes())));
    }
}
