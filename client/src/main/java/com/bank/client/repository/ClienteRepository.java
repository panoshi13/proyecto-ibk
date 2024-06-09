package com.bank.client.repository;

import com.bank.client.model.Cliente;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Repository
public interface ClienteRepository extends ReactiveCrudRepository<Cliente, Long> {
    Mono<Cliente> findByCodigoUnico(String codigoUnico);
}