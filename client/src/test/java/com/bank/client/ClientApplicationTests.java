package com.bank.client;

import com.bank.client.controller.ClienteController;
import com.bank.client.dto.ClienteDto;
import com.bank.client.service.ClienteService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
class ClientApplicationTests {

	@Autowired
	private ClienteController clienteController;

	@MockBean
	private ClienteService clienteService;

	@Test
	public void testGetAllClientes() {
		// Mocking
		when(clienteService.getAllClientes()).thenReturn(Flux.fromIterable(Arrays.asList(
				new ClienteDto("e41df37a-c737-4269-b29e-7ef731064558", "Julio","Tello","Av. Brasil 4512","987654536","julio.tello@gmail.com","DNI","78654452"),
				new ClienteDto("e41df37a-c737-4269-b29e-7ef731064111", "Pedro","Suarez","Av. Brasil 2123","987654536","julio.tello@gmail.com","DNI","78654422"))));

		// Test
		ResponseEntity<?> response = clienteController.getAllClientes("testAppId");

		// Assertions
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(2, ((Flux<?>) Objects.requireNonNull(response.getBody())).count().block());
		verify(clienteService, times(1)).getAllClientes();
	}

	@Test
	public void testGetClienteById() {
		// Mocking
		String codigoUnico = "e41df37a-c737-4269-b29e-7ef731064511";
		when(clienteService.getClienteById(codigoUnico)).thenReturn(Mono.just(new ClienteDto(codigoUnico, "Julio","Tello","Av. Brasil 4512","987654536","julio.tello@gmail.com","DNI","78654452")));

		// Test
		Mono<ResponseEntity<ClienteDto>> responseMono = clienteController.getClienteById("testAppId", codigoUnico);
		ResponseEntity<ClienteDto> response = responseMono.block();

		// Assertions
        assert response != null;
        assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(codigoUnico, Objects.requireNonNull(response.getBody()).getCodigoUnico());
		verify(clienteService, times(1)).getClienteById(codigoUnico);
	}
}
