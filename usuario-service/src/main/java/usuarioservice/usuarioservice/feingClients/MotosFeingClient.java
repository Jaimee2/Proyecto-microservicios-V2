package usuarioservice.usuarioservice.feingClients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import usuarioservice.usuarioservice.modelos.Moto;

import java.util.List;

//@FeignClient(name = "moto-service" , url = "http://moto-service", path = "/motos")
//Eureka:
@FeignClient(name = "moto-service" ,path = "/motos")
public interface MotosFeingClient {

    @PostMapping
    public Moto save(@RequestBody Moto moto);

    @GetMapping("/usuario/{usuarioId}")
    public List<Moto> getMotos(@PathVariable("usuarioId") int usuarioId);

}
