package Logistica.logistica.Assemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import Logistica.logistica.Controlador.PedidoControladorV2;
import Logistica.logistica.Modelo.Pedido;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
public class PedidoModelAssembler implements RepresentationModelAssembler<Pedido, EntityModel<Pedido>> {
    @Override
    public @NonNull EntityModel<Pedido> toModel(@NonNull Pedido pedido) {
        return EntityModel.of(pedido,
                linkTo(methodOn(PedidoControladorV2.class).getPedidoById(pedido.getIdPedido())).withSelfRel(),
                linkTo(methodOn(PedidoControladorV2.class).getAllPedidos()).withRel("pedidos"));
    }
}
