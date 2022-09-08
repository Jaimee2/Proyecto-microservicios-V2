package usuarioservice.usuarioservice.controladores;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import usuarioservice.usuarioservice.modelos.Coche;
import usuarioservice.usuarioservice.modelos.Moto;
import usuarioservice.usuarioservice.modelos.Usuario;
import usuarioservice.usuarioservice.servicios.UsuarioService;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @RequestMapping
    public ResponseEntity<List<Usuario>> listarUsuarios() {

        List<Usuario> listaUsuarios = usuarioService.getAll();

        if (listaUsuarios.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(listaUsuarios);
        }
    }

    @RequestMapping("/{id}")
    public ResponseEntity<Usuario> obtenerUsuario(@PathVariable("id") int id) {

        Usuario usuario = usuarioService.getUsuarioById(id);

        if (usuario == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(usuario);
        }
    }

    @PostMapping
    public ResponseEntity<Usuario> guardarUsuario(@RequestBody Usuario usuario) {
        usuarioService.save(usuario);
        return ResponseEntity.ok(usuario);
    }

    @RequestMapping("/coches/{usuarioId}")
    @CircuitBreaker(name = "circuitbreaker-coche", fallbackMethod = "getFallBackCoches")
    public ResponseEntity<List<Coche>> getCochesByUsuarioId(@PathVariable("usuarioId") int id){

        List<Coche> listaCoches = usuarioService.getCochesByUsuarioId(id);

        if (listaCoches == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(listaCoches);
        }
    }

    //Si el circuito con coche está cerrado entramos aquí
    public ResponseEntity<List<Coche>> getFallBackCoches(@PathVariable("usuarioId") int id, RuntimeException e){
        System.out.println("Entramos en el fallBack de coches");
        return new ResponseEntity("el usuario  " + id + " tiene el coche en el taller :(", HttpStatus.OK);

    }

    @RequestMapping("/motos/{usuarioId}")
    @CircuitBreaker(name = "circuitbreaker-moto", fallbackMethod = "getFallBackMotos")
    public ResponseEntity<List<Moto>> getMotosByUsuarioId(@PathVariable("usuarioId") int id){

        List<Moto> listaMotos = usuarioService.getMotosByUsuarioId(id);

        if (listaMotos == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(listaMotos);
        }
    }
    //Si el circuito con coche está cerrado entramos aquí
    public ResponseEntity<List<Moto>> getFallBackMotos(@PathVariable("usuarioId") int id, RuntimeException e){
        System.out.println("Entramos en el fallBack de motos");
        return new ResponseEntity("el usuario  " + id + " tiene la moto en el taller :(", HttpStatus.OK);

    }

    @PostMapping("/coche/{usuarioId}")
    public ResponseEntity<Coche> guardarCoche(@PathVariable("usuarioId") int usuarioId, @RequestBody Coche coche){
        return ResponseEntity.ok(usuarioService.saveCoche(usuarioId,coche));
    }

    @PostMapping("/moto/{usuarioId}")
    public ResponseEntity<Moto> guardarMoto(@PathVariable("usuarioId") int usuarioId, @RequestBody Moto moto){
        return ResponseEntity.ok(usuarioService.saveMoto(usuarioId,moto));
    }

    @GetMapping("/todos/{usuarioId}")
    public  ResponseEntity<Map<String,Object>> listarTodosLosVehiculos(@PathVariable("usuarioId") int usuarioId){
        return ResponseEntity.ok(usuarioService.getUsuarioAndVehiculos(usuarioId));
    }
}
