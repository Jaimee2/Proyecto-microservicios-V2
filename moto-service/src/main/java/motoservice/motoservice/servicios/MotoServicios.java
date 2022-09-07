package motoservice.motoservice.servicios;

import motoservice.motoservice.modelos.Moto;
import motoservice.motoservice.repositorios.MotoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MotoServicios {
    @Autowired
    private MotoRepositorio motoRepositorio;

    public List<Moto> getAll() {
        return motoRepositorio.findAll();
    }

    public Moto getMotoById(int id) {
        return motoRepositorio.findById(id).orElse(null);
    }

    public Moto save(Moto moto) {
        motoRepositorio.save(moto);
        return moto;
    }

    public List<Moto> getMotoByUsuario(int usuarioId){
        return motoRepositorio.findByUsuarioId(usuarioId);
    }
}
