package com.pagos.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("pagos")
public class Pago {

    @Id
    private Long id;
    private Long ordenId;
    private Double monto;
    private String estado;

    public Pago() {}

    public Pago(Long id, Long ordenId, Double monto, String estado) {
        this.id = id;
        this.ordenId = ordenId;
        this.monto = monto;
        this.estado = estado;
    }

    public Long getId() {
        return id;
    }

    public Long getOrdenId() {
        return ordenId;
    }

    public Double getMonto() {
        return monto;
    }

    public String getEstado() {
        return estado;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setOrdenId(Long ordenId) {
        this.ordenId = ordenId;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}