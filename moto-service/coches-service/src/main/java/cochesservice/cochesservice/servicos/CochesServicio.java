package cochesservice.cochesservice.servicos;

import cochesservice.cochesservice.modelos.Coche;
import cochesservice.cochesservice.repositorio.CochesRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CochesServicio {
    @Autowired
    private CochesRepositorio cochesRepositorio;

    public List<Coche> getAll() {
        return cochesRepositorio.findAll();
    }

    public Coche getCocheById(int id) {
        return cochesRepositorio.findById(id).orElse(null);
    }

    public Coche save(Coche coche) {
        cochesRepositorio.save(coche);
        return coche;
    }

    public List<Coche> getCarrosByUsuario(int usuarioId){
        return cochesRepositorio.findByUsuarioId(usuarioId);
    }
}
