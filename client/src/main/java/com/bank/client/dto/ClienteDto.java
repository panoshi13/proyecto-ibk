package com.bank.client.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDto {
    private String codigoUnico;
    private String nombre;
    private String apellido;
    private String direccion;
    private String telefono;
    private String email;
    private String tipoDocumento;
    private String numeroDocumento;
}
