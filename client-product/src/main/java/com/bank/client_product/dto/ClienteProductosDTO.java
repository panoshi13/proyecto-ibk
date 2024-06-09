package com.bank.client_product.dto;

import lombok.Data;

import java.util.List;

@Data
public class ClienteProductosDTO {
    private String codigoUnico;
    private String nombre;
    private String apellido;
    private String tipoDocumento;
    private String numeroDocumento;
    private List<ProductoFinancieroDTO> productos;
}
