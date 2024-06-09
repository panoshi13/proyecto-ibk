package com.bank.client.service;

import com.bank.client.dto.ClienteDto;
import com.bank.client.mapper.ClienteMapper;
import com.bank.client.model.Cliente;
import com.bank.client.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ClienteService {
    private final ClienteRepository clienteRepository;

    public Flux<ClienteDto> getAllClientes() {
        return clienteRepository.findAll()
                .map(ClienteMapper.CLIENTE_MAPPER::toClienteDto);
    }

    public Mono<ClienteDto> getClienteById(String clienteId) {
        return clienteRepository.findByCodigoUnico(clienteId)
                .map(ClienteMapper.CLIENTE_MAPPER::toClienteDto);
    }

    public Mono<Cliente> saveCliente(ClienteDto clienteDto) {
        Cliente cliente = new Cliente();
        cliente.setNombre(clienteDto.getNombre());
        cliente.setApellido(clienteDto.getApellido());
        cliente.setDireccion(clienteDto.getDireccion());
        cliente.setTipoDocumento(clienteDto.getTipoDocumento());
        cliente.setTelefono(clienteDto.getTelefono());
        cliente.setNumeroDocumento(clienteDto.getNumeroDocumento());
        cliente.setEmail(clienteDto.getEmail());
        cliente.setFechaCreacion(LocalDateTime.now());
        cliente.setCodigoUnico(UUID.randomUUID().toString());
        return clienteRepository.save(cliente);
    }


    public Mono<Void> deleteCliente(Long clienteId) {
        return clienteRepository.deleteById(clienteId);
    }
}