package udb.edu.sv.Desafio2.controller;

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

@PostMapping
    public ResponseEntity<Usuarios> crearUsuario(@Valid @RequestBody UsuariosDTo usuariosDTo) {
    Usuarios nuevoUsuario = new Usuarios();
    nuevoUsuario.setNombre(usuariosDTo.getNombre());
    nuevoUsuario.setApellido(usuariosDTo.getApellido());
    nuevoUsuario.setEmail(usuariosDTo.getEmail());

    Usuarios savedUsuario = usuariosRepository.save(nuevoUsuario);
    return ResponseEntity.ok(savedUsuario);
}

//Obtener la lista de todos los usuarios
    @GetMapping
    public ResponseEntity<List<Usuarios>> listarUsuarios() {
    return ResponseEntity.ok(usuariosRepository.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Usuarios> obtenerPorId(@PathVariable Long id) {
        return usuariosRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    //Actualizar un usuario
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

    //Eliminar un usuario
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable Long id) {
        if (usuariosRepository.existsById(id)) {
            usuariosRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
