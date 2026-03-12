package com.pagos.repository;

import com.pagos.model.Pago;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface PagoRepository extends ReactiveCrudRepository<Pago, Long> {
}
