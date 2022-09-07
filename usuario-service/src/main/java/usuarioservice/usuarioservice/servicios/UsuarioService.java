package usuarioservice.usuarioservice.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import usuarioservice.usuarioservice.feingClients.CochesFeingClient;
import usuarioservice.usuarioservice.feingClients.MotosFeingClient;
import usuarioservice.usuarioservice.modelos.Coche;
import usuarioservice.usuarioservice.modelos.Moto;
import usuarioservice.usuarioservice.modelos.Usuario;
import usuarioservice.usuarioservice.repositorios.UsuarioRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private CochesFeingClient cochesFeingClient;

    @Autowired
    private MotosFeingClient motosFeingClient;

    public List<Usuario> getAll() {
        return usuarioRepository.findAll();
    }

    public Usuario getUsuarioById(int id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    public Usuario save(Usuario usuario) {
        Usuario nuevoUsuario = usuarioRepository.save(usuario);
        return nuevoUsuario;
    }

    //Devolvemos una lista de coches que depende del id del usuario
    public List<Coche> getCochesByUsuarioId(int usuarioId){
        //Query al servicio coches                          //Esto se tendrá que cambiar con heroku
        List<Coche> listaCoches = restTemplate.getForObject("http://localhost:8082/coche/usuario/"+usuarioId,List.class);
        return listaCoches;
    }

    //Devolvemos una lista de motos que depende del id del usuario
    public List<Coche> getMotosByUsuarioId(int usuarioId){
        //Query al servicio coches                          //Esto se tendrá que cambiar con heroku
        List<Coche> listaMotos = restTemplate.getForObject("http://localhost:8083/motos/usuario/"+usuarioId,List.class);
        return listaMotos;
    }

    //Llamamos al micro servico coches y añadimos una nueva instancia
    public Coche saveCoche(int usuarioId,Coche coche){
        coche.setUsuarioId(usuarioId);
        return cochesFeingClient.save(coche);
    }

    //Llamamos al micro servico motos y añadimos una nueva instancia
    public Moto saveMoto(int usuarioId, Moto moto){
        moto.setUsuarioId(usuarioId);
        return motosFeingClient.save(moto);
    }

    public Map<String, Object> getUsuarioAndVehiculos(int usuarioId) {
        Map<String, Object> resultado = new HashMap<>();

        Usuario usuario = usuarioRepository.findById(usuarioId).orElse(null);

        if (usuario == null) {
            resultado.put("Mensaje", "El usuario no existe");
            return resultado;
        } else {
            resultado.put("Usuario", usuario);
            List<Coche> listaCoches = cochesFeingClient.getCoches(usuarioId);
            if (listaCoches.isEmpty()) {
                resultado.put("Coches", "El usuario no tiene coches");
            } else {
                resultado.put("Coches", listaCoches);
            }
            List<Moto> listaMotos = motosFeingClient.getMotos(usuarioId);
            if (listaMotos.isEmpty()) {
                resultado.put("Motos", "El usuario no tiene motos");
            } else {
                resultado.put("Motos", listaMotos);
            }

            return resultado;
        }
    }


}
