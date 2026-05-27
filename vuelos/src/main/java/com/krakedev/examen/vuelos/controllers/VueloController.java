package com.krakedev.examen.vuelos.controllers;

import com.krakedev.examen.vuelos.entities.Vuelo;
import com.krakedev.examen.vuelos.services.VueloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/vuelos")
public class VueloController {

    @Autowired
    private VueloService vueloService;

    @PostMapping
    public ResponseEntity<?> insertar(@RequestBody Vuelo vuelo) {
        try {
            Vuelo nuevoVuelo = vueloService.guardar(vuelo);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoVuelo);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(crearErrorResponse(e.getMessage()));
        }
    }

    @GetMapping
    public ResponseEntity<?> listarTodos() {
        try {
            return ResponseEntity.ok(vueloService.listarTodos());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(crearErrorResponse(e.getMessage()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        try {
            return vueloService.buscarPorId(id)
                    .<ResponseEntity<?>>map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(crearErrorResponse("ID no encontrado")));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(crearErrorResponse(e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody Vuelo vuelo) {
        try {
            Vuelo actualizado = vueloService.actualizar(id, vuelo);
            return ResponseEntity.ok(actualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(crearErrorResponse(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(crearErrorResponse(e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        try {
            vueloService.eliminar(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(crearErrorResponse(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(crearErrorResponse(e.getMessage()));
        }
    }

    @GetMapping("/asientos-mayores/{asientos}")
    public ResponseEntity<?> listarPorAsientos(@PathVariable Integer asientos) {
        try {
            return ResponseEntity.ok(vueloService.listarPorAsientosMayoresA(asientos));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(crearErrorResponse(e.getMessage()));
        }
    }

    private Map<String, String> crearErrorResponse(String mensaje) {
        Map<String, String> error = new HashMap<>();
        error.put("error", mensaje);
        return error;
    }
}