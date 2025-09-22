package udb.edu.sv.Desafio2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import udb.edu.sv.Desafio2.Suscripcion;

@Repository
public interface SuscripcionRepository extends JpaRepository<Suscripcion, Long> {
}
