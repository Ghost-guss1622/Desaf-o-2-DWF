package udb.edu.sv.Desafio2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import udb.edu.sv.Desafio2.Usuarios;

@Repository
public interface UsuariosRepository extends JpaRepository<Usuarios, Long> {
}
