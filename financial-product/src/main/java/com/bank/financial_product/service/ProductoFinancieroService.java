package com.bank.financial_product.service;

import com.bank.financial_product.dto.ProductoDto;
import com.bank.financial_product.mapper.ProductoMapper;
import com.bank.financial_product.model.ProductoFinanciero;
import com.bank.financial_product.repository.ProductoFinancieroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ProductoFinancieroService {
    private final ProductoFinancieroRepository productoFinancieroRepository;
    public Flux<ProductoDto> getAllProductos() {
        return productoFinancieroRepository.findAll()
                .map(ProductoMapper.MAPPER::toProductoDto);
    }

    public Flux<ProductoDto> getProductoByCodigoUnico(String codigoUnico) {
        return productoFinancieroRepository.findByCodigoUnico(codigoUnico)
                .map(ProductoMapper.MAPPER::toProductoDto);
    }
}
