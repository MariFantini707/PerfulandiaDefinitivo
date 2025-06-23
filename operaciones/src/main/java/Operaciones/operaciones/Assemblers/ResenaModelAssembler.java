package Operaciones.operaciones.Assemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import Operaciones.operaciones.controlador.ResenaControladorV2;
import Operaciones.operaciones.modelo.Resena;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import org.springframework.lang.NonNull;

@Component
public class ResenaModelAssembler implements RepresentationModelAssembler<Resena, EntityModel<Resena>> {
    @Override
    public @NonNull EntityModel<Resena> toModel(@NonNull Resena resena) {
        return EntityModel.of(resena,
                linkTo(methodOn(ResenaControladorV2.class).getResenaById(resena.getIdResena())).withSelfRel(),
                linkTo(methodOn(ResenaControladorV2.class).getAllResenas()).withRel("resenas"));
    }
}
