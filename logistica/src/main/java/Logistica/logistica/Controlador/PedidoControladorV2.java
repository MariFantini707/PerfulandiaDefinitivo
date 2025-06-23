package Logistica.logistica.Controlador;

import Logistica.logistica.Modelo.Pedido;
import Logistica.logistica.Servicio.PedidoServicio;
import Logistica.logistica.Assemblers.PedidoModelAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/v2/pedidos")
public class PedidoControladorV2 {
    @Autowired
    private PedidoServicio pedidoServicio;
    @Autowired
    private PedidoModelAssembler assembler;

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<Pedido>> getAllPedidos() {
        List<EntityModel<Pedido>> pedidos = pedidoServicio.listarPedidos().stream()
            .map(assembler::toModel)
            .collect(Collectors.toList());
        return CollectionModel.of(pedidos,
            linkTo(methodOn(PedidoControladorV2.class).getAllPedidos()).withSelfRel());
    }

    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public EntityModel<Pedido> getPedidoById(@PathVariable Integer id) {
        Pedido pedido = pedidoServicio.obtenerPedidoPorId(id)
            .orElseThrow(() -> new RuntimeException("Pedido no encontrado"));
        return assembler.toModel(pedido);
    }

    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Pedido>> createPedido(@RequestBody Pedido pedido) {
        Pedido nuevoPedido = pedidoServicio.guardarPedido(pedido);
        return ResponseEntity
            .created(linkTo(methodOn(PedidoControladorV2.class).getPedidoById(nuevoPedido.getIdPedido())).toUri())
            .body(assembler.toModel(nuevoPedido));
    }

    @PutMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Pedido>> updatePedido(@PathVariable Integer id, @RequestBody Pedido pedido) {
        pedido.setIdPedido(id);
        Pedido updated = pedidoServicio.actualizarPedido(pedido);
        return ResponseEntity.ok(assembler.toModel(updated));
    }

    @DeleteMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<?> deletePedido(@PathVariable Integer id) {
        pedidoServicio.eliminarPedido(id);
        return ResponseEntity.noContent().build();
    }
}
