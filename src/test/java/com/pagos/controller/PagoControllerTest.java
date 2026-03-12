package com.pagos.controller;

import com.pagos.dto.PagoRequest;
import com.pagos.dto.PagoResponse;
import com.pagos.service.PagoService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

class PagoControllerTest {

    private final PagoService pagoService = Mockito.mock(PagoService.class);

    private final WebTestClient webTestClient =
            WebTestClient.bindToController(new PagoController(pagoService)).build();

    @Test
    void deberiaProcesarPago() {

        PagoRequest request = new PagoRequest(1L, 500.0);

        Mockito.when(pagoService.procesarPago(request))
                .thenReturn(Mono.just(new PagoResponse("APPROVED")));

        webTestClient.post()
                .uri("/pagos")
                .bodyValue(request)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.estado").isEqualTo("APPROVED");
    }
}