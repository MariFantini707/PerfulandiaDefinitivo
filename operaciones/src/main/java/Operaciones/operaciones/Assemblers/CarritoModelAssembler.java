package Operaciones.operaciones.Assemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import Operaciones.operaciones.controlador.CarritoControladorV2;
import Operaciones.operaciones.modelo.Carrito;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
public class CarritoModelAssembler implements RepresentationModelAssembler<Carrito, EntityModel<Carrito>> {
    @Override
    public @NonNull EntityModel<Carrito> toModel(@NonNull Carrito carrito) {
        return EntityModel.of(carrito,
                linkTo(methodOn(CarritoControladorV2.class).getCarritoById(carrito.getIdCarrito())).withSelfRel(),
                linkTo(methodOn(CarritoControladorV2.class).getAllCarritos()).withRel("carritos"));
    }
}
