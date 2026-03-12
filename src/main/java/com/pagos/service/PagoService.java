package com.pagos.service;

import com.pagos.dto.PagoRequest;
import com.pagos.dto.PagoResponse;
import com.pagos.model.Pago;
import com.pagos.repository.PagoRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.function.Predicate;

@Service
public class PagoService {

    private final PagoRepository pagoRepository;

    public PagoService(PagoRepository pagoRepository) {
        this.pagoRepository = pagoRepository;
    }

    // Predicate para validar monto
    private final Predicate<Double> montoAprobado = monto -> monto <= 1000;

    public Mono<PagoResponse> procesarPago(PagoRequest request) {

        String estado = montoAprobado.test(request.monto())
                ? "APPROVED"
                : "REJECTED";

        Pago pago = new Pago();
        pago.setOrdenId(request.ordenId());
        pago.setMonto(request.monto());
        pago.setEstado(estado);

        return pagoRepository.save(pago)
                .map(p -> new PagoResponse(p.getEstado()));
    }
}

