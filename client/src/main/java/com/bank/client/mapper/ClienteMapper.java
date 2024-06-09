package com.bank.client.mapper;

import com.bank.client.dto.ClienteDto;
import com.bank.client.model.Cliente;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ClienteMapper {
    ClienteMapper CLIENTE_MAPPER = Mappers.getMapper(ClienteMapper.class);

    @Mapping(target = "nombre", source = "nombre")
    @Mapping(target = "codigoUnico", source = "codigoUnico")
    @Mapping(target = "apellido", source = "apellido")
    @Mapping(target = "direccion", source = "direccion")
    @Mapping(target = "telefono", source = "telefono")
    @Mapping(target = "tipoDocumento", source = "tipoDocumento")
    @Mapping(target = "numeroDocumento", source = "numeroDocumento")
    @Mapping(target = "email", source = "email")
    ClienteDto toClienteDto(Cliente cliente);
}
