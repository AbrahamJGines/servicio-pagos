package com.pagos.service;

import com.pagos.dto.PagoRequest;
import com.pagos.dto.PagoResponse;
import com.pagos.model.Pago;
import com.pagos.repository.PagoRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.function.Predicate;
import java.util.function.Supplier;

@Service
public class PagoService {

    private final PagoRepository pagoRepository;

    public PagoService(PagoRepository pagoRepository) {
        this.pagoRepository = pagoRepository;
    }

    private final Predicate<Double> esMontoAprobado = monto -> monto <= 10000;

    private final Supplier<String> estadoAprobado = () -> "APPROVED";

    private final Supplier<String> estadoRechazado = () -> "REJECTED";

    public Mono<PagoResponse> procesarPago(PagoRequest request) {

        String estado = esMontoAprobado.test(request.monto())
                ? estadoAprobado.get()
                : estadoRechazado.get();

        Pago pago = new Pago();
        pago.setOrdenId(request.ordenId());
        pago.setMonto(request.monto());
        pago.setEstado(estado);

        return pagoRepository.save(pago)
                .map(p -> new PagoResponse(p.getEstado()));
    }
}

