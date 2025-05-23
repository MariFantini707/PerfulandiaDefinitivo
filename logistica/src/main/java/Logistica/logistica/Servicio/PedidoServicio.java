package Logistica.logistica.Servicio;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import Logistica.logistica.Modelo.Pedido;
import Logistica.logistica.Modelo.ProductoDto;
import Logistica.logistica.Modelo.UsuarioDto;
import Logistica.logistica.Repositorio.PedidoRepositorio;

@Service
public class PedidoServicio {
    @Autowired
    private final PedidoRepositorio pedidoRepositorio;
    @Autowired
    private final RestTemplate restTemplate; //parte ede prueba1

    @Value("${services.usuarios.url}")
    private String sucursalUrl;

    public List<Pedido> listarPedidos() {
        return pedidoRepositorio.findAll();
    }

    public Optional<Pedido> obtenerPedidoPorId(Integer id) {
        return pedidoRepositorio.findById(id);
    }

    public Pedido guardarPedido(Pedido pedido) {
        Pedido savedPedido = pedidoRepositorio.save(pedido);
        try {
            // Llamada a la API de sucursal para aumentar el stock usando el método de ProductoServicio
            String url = sucursalUrl + "/productos/aumentarStock/" + savedPedido.getIdProducto() + "/" + savedPedido.getCantidadPedido();
            restTemplate.put(url, null);
        } catch (Exception e) {
            System.out.println("Error al aumentar el stock en sucursal: " + e.getMessage());
        }
        return savedPedido;
    }

    public Pedido actualizarPedido(Pedido pedido) {
        return pedidoRepositorio.save(pedido);
    }

    public void eliminarPedido(Integer id) {
        pedidoRepositorio.deleteById(id);
    }
    // Prueba1
    public PedidoServicio(PedidoRepositorio pedidoRepositorio, RestTemplate restTemplate) {
        this.pedidoRepositorio = pedidoRepositorio;
        this.restTemplate = restTemplate;
    }
    public UsuarioDto obtenerUsuarioDePedido(Integer idPedido) {
        Pedido pedido = pedidoRepositorio.findById(idPedido)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado"));

        String url = "http://localhost:8081/usuario/" + pedido.getIdUsuario();
        UsuarioDto usuario = restTemplate.getForObject(url, UsuarioDto.class);

        return usuario;
    }

    public ProductoDto obtenerProductoDePedido(Integer idPedido) {
        Pedido pedido = pedidoRepositorio.findById(idPedido)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado"));

        String url = "http://localhost:8085/productos/" + pedido.getIdProducto();
        return restTemplate.getForObject(url, ProductoDto.class);
    }


}
