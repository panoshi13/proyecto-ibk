package com.bank.financial_product.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.io.Serializable;

@Table("producto_financiero")
@Getter
@Setter
public class ProductoFinanciero implements Serializable {
    private static final long serialVersionUID = -327463029589625760L;

    @Id
    @Column("id")
    private Long id;

    @Column("tipoProducto")
    private String tipoProducto;

    @Column("nombreProducto")
    private String nombreProducto;

    @Column("saldo")
    private Double saldo;

    @Column("codigoUnico")
    private String codigoUnico;
}
