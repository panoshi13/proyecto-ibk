package com.bank.financial_product;

import com.bank.financial_product.controller.ProductoFinancieroController;
import com.bank.financial_product.dto.ProductoDto;
import com.bank.financial_product.service.ProductoFinancieroService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@SpringBootTest
class FinancialProductApplicationTests {

	@MockBean
	private ProductoFinancieroService productoFinancieroService;

	@Autowired
	private ProductoFinancieroController productoFinancieroController;

	private WebTestClient webTestClient;

	@BeforeEach
	public void setup() {
		this.webTestClient = WebTestClient.bindToController(productoFinancieroController).build();
	}

	@Test
	public void testGetAllProductos() {
		// Mocking
		when(productoFinancieroService.getAllProductos()).thenReturn(Flux.fromIterable(Arrays.asList(
				new ProductoDto("Tarjeta de debito", "Ahorro Sueldo Soles", 6000.0, "18fd40bd-5e7e-4351-9922-c46e7054295f"),
				new ProductoDto("Tarjeta de credito", "Visa Platinum", 8000.0, "18fd40bd-5e7e-4351-9922-c46e70542000")
		)));

		// Test
		webTestClient.get()
				.uri("/api/productos")
				.header("x-app-id", "testAppId")
				.exchange()
				.expectStatus().isOk()
				.expectBodyList(ProductoDto.class)
				.value(productos -> {
					assertNotNull(productos);
					assertEquals(2, productos.size());
					ProductoDto producto1 = productos.get(0);
					ProductoDto producto2 = productos.get(1);
					assertEquals("18fd40bd-5e7e-4351-9922-c46e7054295f", producto1.getCodigoUnico());
					assertEquals("18fd40bd-5e7e-4351-9922-c46e70542000", producto2.getCodigoUnico());
				});

		// Verify
		verify(productoFinancieroService, times(1)).getAllProductos();
	}

	@Test
	public void testGetProductoByCodigoUnico() {
		// Mocking
		String codigoUnico = "18fd40bd-5e7e-4351-9922-c46e70542000";
		ProductoDto mockProducto = new ProductoDto("Tarjeta de credito", "Visa Platinum", 8000.0, codigoUnico);

		when(productoFinancieroService.getProductoByCodigoUnico(codigoUnico)).thenReturn(Flux.just(mockProducto));

		// Test
		webTestClient.get()
				.uri("/api/productos/clientes/{codigoUnico}", codigoUnico)
				.header("x-app-id", "testAppId")
				.exchange()
				.expectStatus().isOk()
				.expectBodyList(ProductoDto.class)
				.value(productos -> {
					assertNotNull(productos);
					assertEquals(1, productos.size());
					ProductoDto producto = productos.get(0);
					assertEquals(codigoUnico, producto.getCodigoUnico());
					assertEquals("Tarjeta de credito", producto.getTipoProducto());
					assertEquals("Visa Platinum", producto.getNombreProducto());
					assertEquals(8000.0, producto.getSaldo());
				});

		// Verify
		verify(productoFinancieroService, times(1)).getProductoByCodigoUnico(codigoUnico);
	}
}
