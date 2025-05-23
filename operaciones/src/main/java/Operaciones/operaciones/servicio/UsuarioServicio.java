package Operaciones.operaciones.servicio;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Value;
import Operaciones.operaciones.modelo.UsuarioDto;


@Service
public class UsuarioServicio {
    private RestTemplate restTemplate;
    @Value("${services.productos.url}")
    private String productoUrl;

    public UsuarioServicio(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public UsuarioDto obtenerUsuarioPorId(Integer idProducto) {
        String url = productoUrl + "/" + idProducto;
        return restTemplate.getForObject(url, UsuarioDto.class);
    }
    public void actualizarProducto(UsuarioDto usuarioDto) {
        String url = productoUrl + "/" + usuarioDto.getId();
        restTemplate.put(url, usuarioDto);
    }
}
