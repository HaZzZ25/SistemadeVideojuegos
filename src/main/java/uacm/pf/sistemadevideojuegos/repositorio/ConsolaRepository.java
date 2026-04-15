package uacm.pf.sistemadevideojuegos.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uacm.pf.sistemadevideojuegos.modelo.Consola;

@Repository
public interface ConsolaRepository extends JpaRepository<Consola, Integer> {
}