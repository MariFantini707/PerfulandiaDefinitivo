package Sucursal.sucursal.Assemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import Sucursal.sucursal.Controlador.SucursalControladorV2;
import Sucursal.sucursal.Modelo.Sucursal;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
public class SucursalModelAssembler implements RepresentationModelAssembler<Sucursal, EntityModel<Sucursal>> {
    @Override
    public @NonNull EntityModel<Sucursal> toModel(@NonNull Sucursal sucursal) {
        return EntityModel.of(sucursal,
                linkTo(methodOn(SucursalControladorV2.class).getSucursalById(sucursal.getIdSucursal())).withSelfRel(),
                linkTo(methodOn(SucursalControladorV2.class).getAllSucursales()).withRel("sucursales"));
    }
}
