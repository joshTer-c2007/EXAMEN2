package com.krakedev.examen.vuelos.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "vuelos")
public class Vuelo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 10)
    private String codigo;

    @Column(name = "precio_boleto", nullable = false, precision = 10, scale = 2)
    private BigDecimal precioBoleto;

    @Column(name = "asientos_disponibles", nullable = false)
    private Integer asientosDisponibles;

    @Column(name = "destino", length = 100)
    private String destino;

    public Vuelo() {}

    public Vuelo(Long id, String codigo, BigDecimal precioBoleto, Integer asientosDisponibles, String destino) {
        this.id = id;
        this.codigo = codigo;
        this.precioBoleto = precioBoleto;
        this.asientosDisponibles = asientosDisponibles;
        this.destino = destino;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }

    public BigDecimal getPrecioBoleto() { return precioBoleto; }
    public void setPrecioBoleto(BigDecimal precioBoleto) { this.precioBoleto = precioBoleto; }

    public Integer getAsientosDisponibles() { return asientosDisponibles; }
    public void setAsientosDisponibles(Integer asientosDisponibles) { this.asientosDisponibles = asientosDisponibles; }

    public String getDestino() { return destino; }
    public void setDestino(String destino) { this.destino = destino; }
}