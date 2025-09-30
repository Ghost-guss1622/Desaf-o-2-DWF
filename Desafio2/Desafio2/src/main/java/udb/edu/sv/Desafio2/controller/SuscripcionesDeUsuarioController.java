package udb.edu.sv.Desafio2.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import udb.edu.sv.Desafio2.Dto.SuscripcionesDeUsuarioDTo;
import udb.edu.sv.Desafio2.service.SuscripcionesDeUsuarioService;

import java.util.List;

@RestController
@RequestMapping("/api/suscripciones_de_usuarios")
public class SuscripcionesDeUsuarioController {

    @Autowired
    private SuscripcionesDeUsuarioService suscripcionesDeUsuarioService;

    @Operation(summary = "Crear una nueva suscripción de usuario", description = "Asocia un usuario a un plan de suscripción.")
    @ApiResponse(responseCode = "200", description = "Suscripción de usuario creada exitosamente")
    @ApiResponse(responseCode = "400", description = "Error de validación o ID de usuario/suscripción no encontrado")
    @PostMapping
    public ResponseEntity<SuscripcionesDeUsuarioDTo> crearSuscripcionDeUsuario(@Valid @RequestBody SuscripcionesDeUsuarioDTo dto) {
        try {
            SuscripcionesDeUsuarioDTo savedDto = suscripcionesDeUsuarioService.crearSuscripcion(dto);
            return ResponseEntity.ok(savedDto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @Operation(summary = "Obtener todas las suscripciones de usuario", description = "Recupera la lista de todas las suscripciones de usuario")
    @ApiResponse(responseCode = "200", description = "Lista de suscripciones recuperada exitosamente")
    @GetMapping
    public ResponseEntity<List<SuscripcionesDeUsuarioDTo>> listarSuscripciones() {
        List<SuscripcionesDeUsuarioDTo> lista = suscripcionesDeUsuarioService.listarTodos();
        return ResponseEntity.ok(lista);
    }

    @Operation(summary = "Obtener suscripción de usuario por ID", description = "Recupera una suscripción de usuario específica por su ID")
    @ApiResponse(responseCode = "200", description = "Suscripción de usuario encontrada")
    @ApiResponse(responseCode = "404", description = "Suscripción de usuario no encontrada")
    @GetMapping("/{id}")
    public ResponseEntity<SuscripcionesDeUsuarioDTo> obtenerPorId(@PathVariable Long id) {
        return suscripcionesDeUsuarioService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Actualizar una suscripción de usuario", description = "Actualiza la información de una suscripción de usuario existente por ID")
    @ApiResponse(responseCode = "200", description = "Suscripción de usuario actualizada exitosamente")
    @ApiResponse(responseCode = "400", description = "Error de validación o ID de usuario/suscripción no encontrado")
    @ApiResponse(responseCode = "404", description = "Suscripción de usuario no encontrada")
    @PutMapping("/{id}")
    public ResponseEntity<SuscripcionesDeUsuarioDTo> actualizarSuscripciones(
            @PathVariable Long id,
            @Valid @RequestBody SuscripcionesDeUsuarioDTo dto) {
        try {
            SuscripcionesDeUsuarioDTo updatedDto = suscripcionesDeUsuarioService.actualizarSuscripcion(id, dto);
            return ResponseEntity.ok(updatedDto);
        } catch (IllegalArgumentException e) {
            if (e.getMessage().contains("no encontrada con ID: " + id)) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.badRequest().build();
        }
    }

    @Operation(summary = "Eliminar una suscripción de usuario", description = "Elimina una suscripción de usuario existente por ID")
    @ApiResponse(responseCode = "204", description = "Suscripción de usuario eliminada exitosamente")
    @ApiResponse(responseCode = "404", description = "Suscripción de usuario no encontrada")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarSuscripcion(@PathVariable Long id) {
        try {
            suscripcionesDeUsuarioService.eliminarSuscripcion(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
}