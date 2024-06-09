package com.bank.client_product.service;

import com.bank.client_product.dto.ClienteProductosDTO;
import com.bank.client_product.dto.ProductoFinancieroDTO;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class ClienteProductoService {
    private final WebClient.Builder webClientBuilder;
    private final Gson gson;

    public ClienteProductoService(WebClient.Builder webClientBuilder, Gson gson) {
        this.webClientBuilder = webClientBuilder;
        this.gson = gson;
    }

    public Mono<ClienteProductosDTO> getClienteConProductos(String codigoUnico) {
        Mono<String> clienteJsonMono = webClientBuilder.build()
                .get()
                .uri("http://localhost:8081/api/clientes/{codigoUnico}", codigoUnico)
                .header("x-app-id", "ff4c4f58-1")
                .retrieve()
                .bodyToMono(String.class)
                .switchIfEmpty(Mono.error(new RuntimeException("Cliente not found")));

        Mono<String> productosJsonMono = webClientBuilder.build()
                .get()
                .uri("http://localhost:8082/api/productos/clientes/{codigoUnico}", codigoUnico)
                .header("x-app-id", "ff4c4f58-2")
                .retrieve()
                .bodyToMono(String.class)
                .switchIfEmpty(Mono.error(new RuntimeException("Productos not found")));

        return Mono.zip(clienteJsonMono, productosJsonMono)
                .flatMap(tuple -> {
                    String clienteJson = tuple.getT1();
                    String productosJson = tuple.getT2();
                    ClienteProductosDTO cliente = gson.fromJson(clienteJson, ClienteProductosDTO.class);
                    List<ProductoFinancieroDTO> productos = gson.fromJson(productosJson, new TypeToken<List<ProductoFinancieroDTO>>(){}.getType());
                    cliente.setProductos(productos);
                    return Mono.just(cliente);
                });
    }

}