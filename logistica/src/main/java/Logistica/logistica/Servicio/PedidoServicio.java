package Logistica.logistica.Servicio;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import Logistica.logistica.Modelo.Pedido;
import Logistica.logistica.Modelo.UsuarioDto;
//import Logistica.logistica.Modelo.UsuarioDto;
import Logistica.logistica.Repositorio.PedidoRepositorio;

@Service
public class PedidoServicio {
    
    @Autowired
    private PedidoRepositorio pedidoRepositorio;
    private final RestTemplate restTemplate; //parte ede prueba1

    // Listar todos los pedidos
    public List<Pedido> listarPedidos() {
        return pedidoRepositorio.findAll();
    }

    // Obtener un pedido por ID
    public Optional<Pedido> obtenerPedidoPorId(Integer id) {
        return pedidoRepositorio.findById(id);
    }

    // Crear un nuevo pedido
    public Pedido guardarPedido(Pedido pedido) {
        return pedidoRepositorio.save(pedido);
    }

    // Actualizar un pedido existente
    public Pedido actualizarPedido(Pedido pedido) {
        return pedidoRepositorio.save(pedido);
    }

    // Eliminar un pedido por ID
    public void eliminarPedido(Integer id) {
        pedidoRepositorio.deleteById(id);
    }

    //estoy tratando de evitar en lo posible el idUsuario, al final trararé de hacer la conexion entre apis

    //prueba1
    public PedidoServicio(PedidoRepositorio pedidoRepositorio, RestTemplate restTemplate) {
        this.pedidoRepositorio = pedidoRepositorio;
        this.restTemplate = restTemplate;
    }

    public UsuarioDto obtenerUsuarioDePedido(Integer idPedido) {
        Pedido pedido = pedidoRepositorio.findById(idPedido)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado"));

        // Consulta externa usando RestTemplate
        String url = "http://localhost:8081/usuario" + pedido.getIdUsuario();
        UsuarioDto usuario = restTemplate.getForObject(url, UsuarioDto.class);

        return usuario;
    }


}
