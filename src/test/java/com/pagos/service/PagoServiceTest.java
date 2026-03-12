package com.pagos.service;

import com.pagos.dto.PagoRequest;
import com.pagos.repository.PagoRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;

class PagoServiceTest {

    private final PagoRepository pagoRepository = Mockito.mock(PagoRepository.class);
    private final PagoService pagoService = new PagoService(pagoRepository);

    @Test
    void deberiaAprobarPagoCuandoMontoEsMenorA1000() {

        PagoRequest request = new PagoRequest(1L, 500.0);

        Mockito.when(pagoRepository.save(any()))
                .thenAnswer(invocation -> Mono.just(invocation.getArgument(0)));

        StepVerifier.create(pagoService.procesarPago(request))
                .expectNextMatches(response ->
                        response.estado().equals("APPROVED"))
                .verifyComplete();
    }

    @Test
    void deberiaRechazarPagoCuandoMontoEsMayorA1000() {

        PagoRequest request = new PagoRequest(1L, 15000.0);

        Mockito.when(pagoRepository.save(any()))
                .thenAnswer(invocation -> Mono.just(invocation.getArgument(0)));

        StepVerifier.create(pagoService.procesarPago(request))
                .expectNextMatches(response ->
                        response.estado().equals("REJECTED"))
                .verifyComplete();
    }
}