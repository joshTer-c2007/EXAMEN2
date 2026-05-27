package com.krakedev.examen.vuelos.services;

import com.krakedev.examen.vuelos.entities.Vuelo;
import com.krakedev.examen.vuelos.repositories.VueloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class VueloService {

    @Autowired
    private VueloRepository vueloRepository;

    public Vuelo guardar(Vuelo vuelo) {
        return vueloRepository.save(vuelo);
    }

    public List<Vuelo> listarTodos() {
        return vueloRepository.findAll();
    }

    public Optional<Vuelo> buscarPorId(Long id) {
        return vueloRepository.findById(id);
    }

    public Vuelo actualizar(Long id, Vuelo vueloDetalles) {
        Vuelo vuelo = vueloRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vuelo no encontrado con id: " + id));
        
        vuelo.setCodigo(vueloDetalles.getCodigo());
        vuelo.setPrecioBoleto(vueloDetalles.getPrecioBoleto());
        vuelo.setAsientosDisponibles(vueloDetalles.getAsientosDisponibles());
        vuelo.setDestino(vueloDetalles.getDestino());
        
        return vueloRepository.save(vuelo);
    }

    public void eliminar(Long id) {
        Vuelo vuelo = vueloRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vuelo no encontrado con id: " + id));
        vueloRepository.delete(vuelo);
    }

    public List<Vuelo> listarPorAsientosMayoresA(Integer asientos) {
        return vueloRepository.findByAsientosDisponiblesGreaterThan(asientos);
    }
}