package motoservice.motoservice.controladores;

import motoservice.motoservice.modelos.Moto;
import motoservice.motoservice.servicios.MotoServicios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/motos")
public class MotoControlador {

    @Autowired
    private MotoServicios motoServicio;

    @RequestMapping
    public ResponseEntity<List<Moto>> listarMotos() {

        List<Moto> listarMotos = motoServicio.getAll();

        if (listarMotos.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(listarMotos);
        }
    }

    @RequestMapping("/{id}")
    public  ResponseEntity<Moto> obtenerMotoById(@PathVariable("id") int id) {

        Moto moto = motoServicio.getMotoById(id);

        if (moto == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(moto);
        }
    }

    @PostMapping
    public ResponseEntity<Moto> guardarMoto(@RequestBody Moto moto) {
        motoServicio.save(moto);
        return ResponseEntity.ok(moto);
    }

    @RequestMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Moto>> listarMotosPorUsuario(@PathVariable("usuarioId") int  usuarioId) {

        List<Moto> listarMotos = motoServicio.getMotoByUsuario(usuarioId);

        if (listarMotos.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(listarMotos);
        }
    }
}
