package com.bank.client_product.dto;

import lombok.Data;

@Data
public class ProductoFinancieroDTO {
    private String tipoProducto;
    private String nombreProducto;
    private Double saldo;
}
