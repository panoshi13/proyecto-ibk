package com.bank.financial_product.repository;

import com.bank.financial_product.model.ProductoFinanciero;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface ProductoFinancieroRepository extends ReactiveCrudRepository<ProductoFinanciero,Long> {
    Flux<ProductoFinanciero> findByCodigoUnico(String codigoUnico);
}
