package usuarioservice.usuarioservice.feingClients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import usuarioservice.usuarioservice.modelos.Coche;


import java.util.List;

//@FeignClient(name = "coches-service", url = "http://coches-service", path = "/coche")
//Eureka
@FeignClient(name = "coches-service", path = "/coche")
public interface CochesFeingClient {
    @PostMapping
    public Coche save(@RequestBody Coche coche);

    @GetMapping("/usuario/{usuarioId}")
    public List<Coche> getCoches(@PathVariable("usuarioId") int usuarioId);
}
