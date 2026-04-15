package uacm.pf.sistemadevideojuegos.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uacm.pf.sistemadevideojuegos.modelo.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Usuario findByUsuarioAndPassword(String usuario, String password);
}