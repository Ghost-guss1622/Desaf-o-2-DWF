package udb.edu.sv.Desafio2.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import udb.edu.sv.Desafio2.Dto.SuscripcionesDTo;
import udb.edu.sv.Desafio2.Suscripcion;
import udb.edu.sv.Desafio2.repository.SuscripcionRepository;

import java.util.List;

@RestController
@RequestMapping("/api/suscripciones")
public class SuscripcionController {
    @Autowired
    private SuscripcionRepository suscripcionRepository;

    @PostMapping
    public ResponseEntity<Suscripcion> crearSuscripcion(@Valid @RequestBody SuscripcionesDTo suscripcionDTo) {
        Suscripcion suscrip = new Suscripcion();
        suscrip.setNombre(suscripcionDTo.getNombre());
        suscrip.setTipo(suscripcionDTo.getTipo());
        suscrip.setDuracionMeses(suscripcionDTo.getDuracionMeses());
        suscrip.setPrecio(suscripcionDTo.getPrecio());

        Suscripcion suscripciones = suscripcionRepository.save(suscrip);
        return ResponseEntity.ok(suscripciones);
    }

    //Obteer la lista de suscripciones
    @GetMapping
    public ResponseEntity<List<Suscripcion>> listarSuscripciones() {
        return ResponseEntity.ok(suscripcionRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Suscripcion> obtenerPorId(@PathVariable Long id) {
        return suscripcionRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    //Actualizar una suscripcion
    @PutMapping("/{id}")
    public ResponseEntity<Suscripcion> actualizarSucripciones(@PathVariable Long id, @Valid @RequestBody SuscripcionesDTo suscripcionesDTo) {
        return suscripcionRepository.findById(id)
                .map(suscripcionExistente -> {
                    suscripcionExistente.setNombre(suscripcionesDTo.getNombre());
                    suscripcionExistente.setTipo(suscripcionesDTo.getTipo());
                    suscripcionExistente.setDuracionMeses(suscripcionesDTo.getDuracionMeses());
                    suscripcionExistente.setPrecio(suscripcionesDTo.getPrecio());
                    return ResponseEntity.ok(suscripcionRepository.save(suscripcionExistente));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    //Eliminarr una suscripción
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarSuscripcion(@PathVariable Long id) {
        if (suscripcionRepository.existsById(id)) {
            suscripcionRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
