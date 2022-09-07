package motoservice.motoservice.repositorios;

import motoservice.motoservice.modelos.Moto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MotoRepositorio extends JpaRepository<Moto,Integer> {

    List<Moto> findByUsuarioId(int usuarioId); //Muy importante el correcto nombramiento de la función
}
