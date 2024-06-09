package com.bank.financial_product.handler;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;

@Getter
@Setter
@Builder
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HttpException extends RuntimeException  {
    private int status;
    @Nullable
    private ParamErrors errors;
    public HttpException(HttpStatus status, String message, @Nullable ParamErrors errors) {
        super(message);
        Assert.notNull(status, "HttpStatus is required");
        this.status = status.value();
        this.errors = errors;
    }
    public HttpException(HttpStatus status, String message) {
        super(message);
        this.status = status.value();
    }

    public HttpException(HttpStatus status, @Nullable ParamErrors errors) {
        super();
        this.status = status.value();
    }
    public HttpException(HttpStatus status) {
        super();
        this.status = status.value();
    }
    public HttpStatus getStatus() {
        return HttpStatus.valueOf(this.status);
    }
}
