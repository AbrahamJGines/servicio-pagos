package com.pagos.dto;

public record PagoRequest(
        Long ordenId,
        Double monto
) {}
