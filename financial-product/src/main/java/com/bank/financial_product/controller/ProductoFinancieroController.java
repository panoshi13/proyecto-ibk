package com.bank.financial_product.controller;

import com.bank.financial_product.dto.ProductoDto;
import com.bank.financial_product.handler.HttpException;
import com.bank.financial_product.service.ProductoFinancieroService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@Slf4j
@RequestMapping("/api/productos")
@RequiredArgsConstructor
public class ProductoFinancieroController {
    private final ProductoFinancieroService productoFinancieroService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Flux<ProductoDto>> getAllProductos(@RequestHeader("x-app-id") String appId) {
        log.info("RequestId: {}", appId);
        Flux<ProductoDto> productoFinancieroFlux = productoFinancieroService.getAllProductos();
        return ResponseEntity.ok().body(productoFinancieroFlux);
    }

    @GetMapping("/clientes/{codigoUnico}")
    @ResponseStatus(HttpStatus.OK)
    public Flux<ProductoDto> getProductoByCodigoUnico(@RequestHeader("x-app-id") String appId,
                                                      @PathVariable String codigoUnico) {
        log.info("RequestId: {}", appId);
        return productoFinancieroService.getProductoByCodigoUnico(codigoUnico)
                .switchIfEmpty(Mono.error(new HttpException(HttpStatus.NOT_FOUND, "Producto no encontrado")));
    }

}
