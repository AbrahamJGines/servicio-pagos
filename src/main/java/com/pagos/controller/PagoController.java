package com.pagos.controller;

import com.pagos.dto.PagoRequest;
import com.pagos.dto.PagoResponse;
import com.pagos.service.PagoService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/pagos")
public class PagoController {

    private final PagoService pagoService;

    public PagoController(PagoService pagoService) {
        this.pagoService = pagoService;
    }

    @PostMapping
    public Mono<PagoResponse> procesarPago(@RequestBody PagoRequest request) {
        return pagoService.procesarPago(request);
    }
}
