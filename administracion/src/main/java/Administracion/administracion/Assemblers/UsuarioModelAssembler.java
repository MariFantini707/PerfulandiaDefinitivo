package Administracion.administracion.Assemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import Administracion.administracion.Controlador.UsuarioControladorV2;
import Administracion.administracion.Modelo.Usuario;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import org.springframework.lang.NonNull;

@Component
public class UsuarioModelAssembler implements RepresentationModelAssembler<Usuario, EntityModel<Usuario>> {
    @Override
    public @NonNull EntityModel<Usuario> toModel(@NonNull Usuario usuario) {
        return EntityModel.of(usuario,
                linkTo(methodOn(UsuarioControladorV2.class).getUsuarioById(usuario.getIdUsuario())).withSelfRel(),
                linkTo(methodOn(UsuarioControladorV2.class).getAllUsuarios()).withRel("usuarios"));
    }
}
