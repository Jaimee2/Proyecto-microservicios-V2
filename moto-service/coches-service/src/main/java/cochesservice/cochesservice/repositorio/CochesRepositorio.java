package cochesservice.cochesservice.repositorio;

import cochesservice.cochesservice.modelos.Coche;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CochesRepositorio extends JpaRepository<Coche,Integer> {

    List<Coche> findByUsuarioId(int usuarioId); //Muy importante el correcto nombramiento de la función

}
