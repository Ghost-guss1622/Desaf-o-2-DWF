package udb.edu.sv.Desafio2.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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

    @Operation(summary = "Create a new subscription", description = "Creates a new subscription with name, type, duration (months) and price")
    @ApiResponse(responseCode = "200", description = "Subscription successfully created")
    @ApiResponse(responseCode = "400", description = "Validation error")
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

    @Operation(summary = "Get all subscriptions", description = "Retrieve the list of all subscriptions")
    @ApiResponse(responseCode = "200", description = "List of subscriptions retrieved successfully")
    @GetMapping
    public ResponseEntity<List<Suscripcion>> listarSuscripciones() {
        return ResponseEntity.ok(suscripcionRepository.findAll());
    }

    @Operation(summary = "Get subscription by ID", description = "Retrieve a specific subscription by its ID")
    @ApiResponse(responseCode = "200", description = "Subscription found")
    @ApiResponse(responseCode = "404", description = "Subscription not found")
    @GetMapping("/{id}")
    public ResponseEntity<Suscripcion> obtenerPorId(@PathVariable Long id) {
        return suscripcionRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Update a subscription", description = "Update the information of an existing subscription by ID")
    @ApiResponse(responseCode = "200", description = "Subscription successfully updated")
    @ApiResponse(responseCode = "404", description = "Subscription not found")
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

    @Operation(summary = "Delete a subscription", description = "Delete an existing subscription by ID")
    @ApiResponse(responseCode = "204", description = "Subscription successfully deleted")
    @ApiResponse(responseCode = "404", description = "Subscription not found")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarSuscripcion(@PathVariable Long id) {
        if (suscripcionRepository.existsById(id)) {
            suscripcionRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}

