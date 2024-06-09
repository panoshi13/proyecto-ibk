package com.bank.client_product;

import com.bank.client_product.dto.ClienteProductosDTO;
import com.bank.client_product.dto.ProductoFinancieroDTO;
import com.bank.client_product.service.ClienteProductoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureWebTestClient
class ClientProductApplicationTests {

	@Autowired
	private WebTestClient webTestClient;

	@Mock
	private WebClient.Builder webClientBuilder;

	@InjectMocks
	private ClienteProductoService clienteProductoService;

	private WebClient webClient;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		webClient = WebClient.builder().build();
		when(webClientBuilder.build()).thenReturn(webClient);
	}

	@Test
	public void testGetClienteConProductos() {
		// Mock data
		ClienteProductosDTO cliente = new ClienteProductosDTO();
		cliente.setCodigoUnico("123");

		ProductoFinancieroDTO producto1 = new ProductoFinancieroDTO();
		producto1.setTipoProducto("Cuenta Corriente");
		producto1.setNombreProducto("Cuenta Corriente 1");
		producto1.setSaldo(1500.0);

		ProductoFinancieroDTO producto2 = new ProductoFinancieroDTO();
		producto2.setTipoProducto("Tarjeta de Crédito");
		producto2.setNombreProducto("Tarjeta de Crédito 1");
		producto2.setSaldo(5000.0);

		// Mock WebClient responses
		WebClient.RequestHeadersUriSpec requestHeadersUriSpec = Mockito.mock(WebClient.RequestHeadersUriSpec.class);
		WebClient.RequestHeadersSpec requestHeadersSpec = Mockito.mock(WebClient.RequestHeadersSpec.class);
		WebClient.ResponseSpec responseSpec = Mockito.mock(WebClient.ResponseSpec.class);

		when(webClient.get()).thenReturn(requestHeadersUriSpec);
		when(requestHeadersUriSpec.uri("http://localhost:8081/api/clientes/{codigoUnico}", "123")).thenReturn(requestHeadersSpec);
		when(requestHeadersSpec.header("x-app-id", "ff4c4f58-1")).thenReturn(requestHeadersSpec);
		when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
		when(responseSpec.bodyToMono(ClienteProductosDTO.class)).thenReturn(Mono.just(cliente));

		when(webClient.get()).thenReturn(requestHeadersUriSpec);
		when(requestHeadersUriSpec.uri("http://localhost:8082/api/productos/clientes/{codigoUnico}", "123")).thenReturn(requestHeadersSpec);
		when(requestHeadersSpec.header("x-app-id", "ff4c4f58-2")).thenReturn(requestHeadersSpec);
		when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
		when(responseSpec.bodyToFlux(ProductoFinancieroDTO.class)).thenReturn(Flux.just(producto1, producto2));

		// Call the method to test
		Mono<ClienteProductosDTO> result = clienteProductoService.getClienteConProductos("123");

		// Verify the result
		StepVerifier.create(result)
				.expectNextMatches(clienteProductosDTO ->
						clienteProductosDTO.getProductos().size() == 2 &&
								clienteProductosDTO.getProductos().get(0).getTipoProducto().equals("Cuenta Corriente") &&
								clienteProductosDTO.getProductos().get(1).getTipoProducto().equals("Tarjeta de Crédito")
				)
				.verifyComplete();
	}

}