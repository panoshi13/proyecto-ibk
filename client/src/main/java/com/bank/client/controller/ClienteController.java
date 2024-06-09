package com.bank.client.controller;

import com.bank.client.dto.ClienteDto;
import com.bank.client.handler.HttpException;
import com.bank.client.model.Cliente;
import com.bank.client.service.ClienteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@Slf4j
@RequestMapping("/api/clientes")
@RequiredArgsConstructor
public class ClienteController {
    private final ClienteService clienteService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getAllClientes(@RequestHeader("x-app-id") String appId) {
        log.info("RequestId: {}", appId);
        Flux<ClienteDto> clientesFlux = clienteService.getAllClientes();
        return ResponseEntity.ok().body(clientesFlux);
    }

    @GetMapping("/{codigoUnico}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<ResponseEntity<ClienteDto>> getClienteById(@RequestHeader("x-app-id") String appId,
                                                           @PathVariable String codigoUnico) {
        log.info("RequestId: {}", appId);
        return clienteService.getClienteById(codigoUnico)
                .map(ResponseEntity::ok)
                .switchIfEmpty(Mono.error(new HttpException(HttpStatus.NOT_FOUND, "Cliente no encontrado")));
    }


    @PostMapping
    public Mono<Cliente> createCliente(@RequestBody ClienteDto cliente) {
        return clienteService.saveCliente(cliente);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteCliente(@PathVariable Long id) {
        return clienteService.deleteCliente(id);
    }
}