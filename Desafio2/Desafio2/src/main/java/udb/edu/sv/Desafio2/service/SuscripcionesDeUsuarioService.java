package udb.edu.sv.Desafio2.service;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import udb.edu.sv.Desafio2.Dto.SuscripcionesDeUsuarioDTo;
import udb.edu.sv.Desafio2.Dto.SuscripcionesDeUsuarioMapper;
import udb.edu.sv.Desafio2.Suscripcion;
import udb.edu.sv.Desafio2.SuscripcionesDeUsuario;
import udb.edu.sv.Desafio2.Usuarios;
import udb.edu.sv.Desafio2.repository.SuscripcionRepository;
import udb.edu.sv.Desafio2.repository.SuscripcionesDeUsuarioRepository;
import udb.edu.sv.Desafio2.repository.UsuariosRepository;

import java.util.List;
import java.util.Optional;

@Service
public class SuscripcionesDeUsuarioService {

    @Autowired
    private SuscripcionesDeUsuarioRepository suscripcionesDeUsuarioRepository;
    @Autowired
    private UsuariosRepository usuariosRepository;
    @Autowired
    private SuscripcionRepository suscripcionRepository;
    @Autowired
    private SuscripcionesDeUsuarioMapper mapper;

    @Transactional
    public SuscripcionesDeUsuarioDTo crearSuscripcion(SuscripcionesDeUsuarioDTo dto) {
        Usuarios usuario = usuariosRepository.findById(dto.getIdUsuario())
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado con ID: " + dto.getIdUsuario()));

        Suscripcion plan = suscripcionRepository.findById(dto.getIdSuscripcion())
                .orElseThrow(() -> new IllegalArgumentException("Plan de Suscripción no encontrado con ID: " + dto.getIdSuscripcion()));

        //Mapeo inicial
        SuscripcionesDeUsuario nuevaSuscripcion = mapper.mapToEntity(dto, usuario, plan);

        Integer duracion = plan.getDuracionMeses();

        if (duracion != null && duracion > 0) {
            //Plan de pago con duración definida
            nuevaSuscripcion.setFechaFin(dto.getFechaInicio().plusMonths(duracion));
        } else {
            //Plan gratuito
            nuevaSuscripcion.setFechaFin(null);
        }
        nuevaSuscripcion.setEstado("ACTIVA");

        SuscripcionesDeUsuario saved = suscripcionesDeUsuarioRepository.save(nuevaSuscripcion);
        return mapper.mapToDto(saved);
    }

    @Transactional
    public SuscripcionesDeUsuarioDTo actualizarSuscripcion(Long id, SuscripcionesDeUsuarioDTo dto) {
        SuscripcionesDeUsuario suscripcionExistente = suscripcionesDeUsuarioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Suscripción de Usuario no encontrada con ID: " + id));

        Usuarios usuario = usuariosRepository.findById(dto.getIdUsuario())
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado con ID: " + dto.getIdUsuario()));

        Suscripcion plan = suscripcionRepository.findById(dto.getIdSuscripcion())
                .orElseThrow(() -> new IllegalArgumentException("Plan de Suscripción no encontrado con ID: " + dto.getIdSuscripcion()));

        suscripcionExistente.setUsuario(usuario);
        suscripcionExistente.setSuscripcion(plan);
        suscripcionExistente.setFechaInicio(dto.getFechaInicio());

        Integer duracion = plan.getDuracionMeses();

        if (duracion != null && duracion > 0) {
            suscripcionExistente.setFechaFin(dto.getFechaInicio().plusMonths(duracion));
        } else {
            suscripcionExistente.setFechaFin(null); // Plan gratuito o indefinido
        }

        suscripcionExistente.setEstado("ACTIVA");

        SuscripcionesDeUsuario updated = suscripcionesDeUsuarioRepository.save(suscripcionExistente);
        return mapper.mapToDto(updated);
    }

    @Transactional
    public void eliminarSuscripcion(Long id) {
        if (!suscripcionesDeUsuarioRepository.existsById(id)) {
            throw new IllegalArgumentException("Suscripción de Usuario no encontrada con ID: " + id);
        }
        suscripcionesDeUsuarioRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<SuscripcionesDeUsuarioDTo> listarTodos() {
        return suscripcionesDeUsuarioRepository.findAll().stream()
                .map(mapper::mapToDto)
                .toList();
    }
    @Transactional(readOnly = true)
    public Optional<SuscripcionesDeUsuarioDTo> obtenerPorId(Long id) {
        return suscripcionesDeUsuarioRepository.findById(id)
                .map(mapper::mapToDto);
    }
}
