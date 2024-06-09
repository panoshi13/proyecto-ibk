package com.bank.client_product.controller;

import com.bank.client_product.dto.ClienteProductosDTO;
import com.bank.client_product.dto.ProductoFinancieroDTO;
import com.bank.client_product.service.ClienteProductoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@Slf4j
@RequestMapping("/api/cliente-producto")
@RequiredArgsConstructor
public class ClienteProductoController {
    private final ClienteProductoService clienteProductoService;

    @GetMapping("/{codigoUnico}")
    public Mono<ClienteProductosDTO> getClienteConProductos(@PathVariable String codigoUnico) {
        log.info("Solicitud recibida para código único: {}", codigoUnico);
        return clienteProductoService.getClienteConProductos(codigoUnico);
    }
}
