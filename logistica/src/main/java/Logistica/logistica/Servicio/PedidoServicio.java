package Logistica.logistica.Servicio;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Logistica.logistica.Modelo.Pedido;
import Logistica.logistica.Repositorio.PedidoRepositorio;

@Service
public class PedidoServicio {
    
    @Autowired
    private PedidoRepositorio pedidoRepositorio;

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
}
