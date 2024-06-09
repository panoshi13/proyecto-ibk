package com.bank.financial_product.mapper;

import com.bank.financial_product.dto.ProductoDto;
import com.bank.financial_product.model.ProductoFinanciero;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductoMapper {
    ProductoMapper MAPPER = Mappers.getMapper(ProductoMapper.class);

    @Mapping(target = "tipoProducto", source = "tipoProducto")
    @Mapping(target = "nombreProducto", source = "nombreProducto")
    @Mapping(target = "saldo", source = "saldo")
    @Mapping(target = "codigoUnico", source = "codigoUnico")
    ProductoDto toProductoDto(ProductoFinanciero productoFinanciero);
}
