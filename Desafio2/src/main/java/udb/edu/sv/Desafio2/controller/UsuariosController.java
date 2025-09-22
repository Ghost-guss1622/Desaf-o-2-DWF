package udb.edu.sv.Desafio2.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import udb.edu.sv.Desafio2.Dto.UsuariosDTo;
import udb.edu.sv.Desafio2.Usuarios;
import udb.edu.sv.Desafio2.repository.UsuariosRepository;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuariosController {

    @Autowired
    private UsuariosRepository usuariosRepository;

    @Operation(summary = "Create a new user", description = "Creates a new user with name, last name and email")
    @ApiResponse(responseCode = "200", description = "User successfully created")
    @ApiResponse(responseCode = "400", description = "Validation error")
    @PostMapping
    public ResponseEntity<Usuarios> crearUsuario(@Valid @RequestBody UsuariosDTo usuariosDTo) {
        Usuarios nuevoUsuario = new Usuarios();
        nuevoUsuario.setNombre(usuariosDTo.getNombre());
        nuevoUsuario.setApellido(usuariosDTo.getApellido());
        nuevoUsuario.setEmail(usuariosDTo.getEmail());

        Usuarios savedUsuario = usuariosRepository.save(nuevoUsuario);
        return ResponseEntity.ok(savedUsuario);
    }

    @Operation(summary = "Get all users", description = "Retrieve the list of all users")
    @ApiResponse(responseCode = "200", description = "List of users retrieved successfully")
    @GetMapping
    public ResponseEntity<List<Usuarios>> listarUsuarios() {
        return ResponseEntity.ok(usuariosRepository.findAll());
    }

    @Operation(summary = "Get user by ID", description = "Retrieve a specific user by its ID")
    @ApiResponse(responseCode = "200", description = "User found")
    @ApiResponse(responseCode = "404", description = "User not found")
    @GetMapping("/{id}")
    public ResponseEntity<Usuarios> obtenerPorId(@PathVariable Long id) {
        return usuariosRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Update a user", description = "Update the information of an existing user by ID")
    @ApiResponse(responseCode = "200", description = "User successfully updated")
    @ApiResponse(responseCode = "404", description = "User not found")
    @PutMapping("/{id}")
    public ResponseEntity<Usuarios> actualizarUsuario(@PathVariable Long id, @Valid @RequestBody UsuariosDTo usuariosDTo) {
        return usuariosRepository.findById(id)
                .map(usuarioExistente -> {
                    usuarioExistente.setNombre(usuariosDTo.getNombre());
                    usuarioExistente.setApellido(usuariosDTo.getApellido());
                    usuarioExistente.setEmail(usuariosDTo.getEmail());
                    return ResponseEntity.ok(usuariosRepository.save(usuarioExistente));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Delete a user", description = "Delete an existing user by ID")
    @ApiResponse(responseCode = "204", description = "User successfully deleted")
    @ApiResponse(responseCode = "404", description = "User not found")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable Long id) {
        if (usuariosRepository.existsById(id)) {
            usuariosRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}

