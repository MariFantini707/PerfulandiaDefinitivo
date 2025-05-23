package Operaciones.operaciones.servicio;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Value;
import Operaciones.operaciones.modelo.UsuarioDto;


@Service
public class UsuarioServicio {
    private RestTemplate restTemplate;
    @Value("${services.usuarios.url}")
    private String usuarioUrl;

    public UsuarioServicio(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public UsuarioDto getById(Integer idUsuario) {
        String url = usuarioUrl + "/" + idUsuario;
        return restTemplate.getForObject(url, UsuarioDto.class);
    }
    public void update(UsuarioDto usuarioDto) {
        String url = usuarioUrl + "/" + usuarioDto.getId();
        restTemplate.put(url, usuarioDto);
    }
}
