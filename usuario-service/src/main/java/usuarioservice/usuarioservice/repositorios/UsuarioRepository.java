package usuarioservice.usuarioservice.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import usuarioservice.usuarioservice.modelos.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario,Integer> { }
