package com.bank.client.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.io.Serializable;
import java.time.LocalDateTime;

@Table("cliente")
@Getter
@Setter
public class Cliente implements Serializable {
    private static final long serialVersionUID = -327463029589625760L;

    @Id
    @Column("id")
    private Long id;

    @Column("nombre")
    private String nombre;

    @Column("codigoUnico")
    private String codigoUnico;

    @Column("apellido")
    private String apellido;

    @Column("direccion")
    private String direccion;

    @Column("telefono")
    private String telefono;

    @Column("email")
    private String email;

    @Column("tipoDocumento")
    private String tipoDocumento;

    @Column("numeroDocumento")
    private String numeroDocumento;

    @Column("fechaCreacion")
    private LocalDateTime fechaCreacion;
}