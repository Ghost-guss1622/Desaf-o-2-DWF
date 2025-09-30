package udb.edu.sv.Desafio2.Dto;

import org.springframework.stereotype.Component;
import udb.edu.sv.Desafio2.Suscripcion;
import udb.edu.sv.Desafio2.SuscripcionesDeUsuario;
import udb.edu.sv.Desafio2.Usuarios;

@Component
public class SuscripcionesDeUsuarioMapper {

    public SuscripcionesDeUsuarioDTo mapToDto(SuscripcionesDeUsuario entity) {
        SuscripcionesDeUsuarioDTo dto = new SuscripcionesDeUsuarioDTo();

        dto.setId(entity.getId());
        dto.setIdUsuario(entity.getUsuario().getId());
        dto.setIdSuscripcion(entity.getSuscripcion().getId());
        dto.setFechaInicio(entity.getFechaInicio());
        dto.setFechaFin(entity.getFechaFin());
        dto.setEstado(entity.getEstado());

        return dto;
    }

    //Mapeo del DTO
    public SuscripcionesDeUsuario mapToEntity(SuscripcionesDeUsuarioDTo dto, Usuarios usuario, Suscripcion suscripcion) {
        SuscripcionesDeUsuario entity = new SuscripcionesDeUsuario();

        if (dto.getId() != null) {
            entity.setId(dto.getId());
        }

        entity.setUsuario(usuario);
        entity.setSuscripcion(suscripcion);
        entity.setFechaInicio(dto.getFechaInicio());
        entity.setFechaFin(dto.getFechaFin());

        entity.setEstado(dto.getEstado() != null ? dto.getEstado() : "ACTIVA");
        return entity;
    }
}