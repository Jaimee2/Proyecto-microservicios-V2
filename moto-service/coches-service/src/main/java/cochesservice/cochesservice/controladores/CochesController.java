package cochesservice.cochesservice.controladores;

import cochesservice.cochesservice.modelos.Coche;
import cochesservice.cochesservice.servicos.CochesServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/coche")
public class CochesController {

    @Autowired
    private CochesServicio cochesServicio;

    @RequestMapping
    public ResponseEntity<List<Coche>> listarCoches() {

        List<Coche> listarCoches = cochesServicio.getAll();

        if (listarCoches.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(listarCoches);
        }
    }

    @RequestMapping("/{id}")
    public  ResponseEntity<Coche> obtenerCocheById(@PathVariable("id") int id) {

        Coche coche = cochesServicio.getCocheById(id);

        if (coche == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(coche);
        }
    }

    @PostMapping
    public ResponseEntity<Coche> guardarCoche(@RequestBody Coche coche) {
        cochesServicio.save(coche);
        return ResponseEntity.ok(coche);
    }

    @RequestMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Coche>> listarCochesPorUsuario(@PathVariable("usuarioId") int  usuarioId) {

        List<Coche> listarCoches = cochesServicio.getCarrosByUsuario(usuarioId);

        if (listarCoches.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(listarCoches);
        }
    }

}
