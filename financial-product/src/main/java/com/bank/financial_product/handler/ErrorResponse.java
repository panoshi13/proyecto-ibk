package com.bank.financial_product.handler;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@ToString
public class ErrorResponse
{
    private Integer status;
    private String message;
    private String timestamp;
    private String path;
    private ParamErrors errors;
    private String trace;
}

