package com.bank.financial_product.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductoDto {
    private String tipoProducto;
    private String nombreProducto;
    private Double saldo;
    private String codigoUnico;
}
