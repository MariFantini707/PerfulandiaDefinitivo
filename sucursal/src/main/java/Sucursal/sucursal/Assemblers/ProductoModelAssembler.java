package Sucursal.sucursal.Assemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import Sucursal.sucursal.Controlador.ProductoControladorV2;
import Sucursal.sucursal.Modelo.Producto;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
public class ProductoModelAssembler implements RepresentationModelAssembler<Producto, EntityModel<Producto>> {
    @Override
    public @NonNull EntityModel<Producto> toModel(@NonNull Producto producto) {
        return EntityModel.of(producto,
                linkTo(methodOn(ProductoControladorV2.class).getProductoById(producto.getIdProducto())).withSelfRel(),
                linkTo(methodOn(ProductoControladorV2.class).getAllProductos()).withRel("productos"));
    }
}
