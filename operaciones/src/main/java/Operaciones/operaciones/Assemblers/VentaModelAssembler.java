package Operaciones.operaciones.Assemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import Operaciones.operaciones.controlador.VentaControladorV2;
import Operaciones.operaciones.modelo.Venta;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
public class VentaModelAssembler implements RepresentationModelAssembler<Venta, EntityModel<Venta>> {
    @Override
    public @NonNull EntityModel<Venta> toModel(@NonNull Venta venta) {
        return EntityModel.of(venta,
                linkTo(methodOn(VentaControladorV2.class).getVentaById(venta.getIdVenta())).withSelfRel(),
                linkTo(methodOn(VentaControladorV2.class).getAllVentas()).withRel("ventas"));
    }
}
