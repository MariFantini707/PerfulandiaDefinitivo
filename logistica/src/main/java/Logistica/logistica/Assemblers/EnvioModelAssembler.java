package Logistica.logistica.Assemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import Logistica.logistica.Controlador.EnvioControladorV2;
import Logistica.logistica.Modelo.Envio;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import org.springframework.lang.NonNull;

@Component
public class EnvioModelAssembler implements RepresentationModelAssembler<Envio, EntityModel<Envio>> {
    @Override
    public @NonNull EntityModel<Envio> toModel(@NonNull Envio envio) {
        return EntityModel.of(envio,
                linkTo(methodOn(EnvioControladorV2.class).getEnvioById(envio.getIdEnvio())).withSelfRel(),
                linkTo(methodOn(EnvioControladorV2.class).getAllEnvios()).withRel("envios"));
    }
}
