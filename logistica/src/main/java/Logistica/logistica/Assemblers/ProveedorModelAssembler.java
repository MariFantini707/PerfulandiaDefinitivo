package Logistica.logistica.Assemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import Logistica.logistica.Controlador.ProveedorControladorV2;
import Logistica.logistica.Modelo.Proveedor;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
public class ProveedorModelAssembler implements RepresentationModelAssembler<Proveedor, EntityModel<Proveedor>> {
    @Override
    public @NonNull EntityModel<Proveedor> toModel(@NonNull Proveedor proveedor) {
        return EntityModel.of(proveedor,
                linkTo(methodOn(ProveedorControladorV2.class).getProveedorById(proveedor.getIdProveedor())).withSelfRel(),
                linkTo(methodOn(ProveedorControladorV2.class).getAllProveedores()).withRel("proveedores"));
    }
}
