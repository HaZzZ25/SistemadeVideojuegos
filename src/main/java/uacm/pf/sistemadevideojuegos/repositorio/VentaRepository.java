package uacm.pf.sistemadevideojuegos.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uacm.pf.sistemadevideojuegos.modelo.Venta;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface VentaRepository extends JpaRepository<Venta, Long> {

    // Usamos SQL puro para comparar únicamente la fecha, ignorando las horas y minutos
    @Query(value = "SELECT * FROM ventas WHERE DATE(fecha) = :fechaBusqueda", nativeQuery = true)
    List<Venta> buscarVentasPorDia(@Param("fechaBusqueda") LocalDate fechaBusqueda);
}