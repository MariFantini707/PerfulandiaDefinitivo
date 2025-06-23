package Soporte.soporte.Assemblers;

import Soporte.soporte.Modelo.TicketSoporte;
import Soporte.soporte.Controlador.TicketSoporteControladorV2;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class TicketSoporteModelAssembler implements RepresentationModelAssembler<TicketSoporte, EntityModel<TicketSoporte>> {

    @Override
    public @NonNull EntityModel<TicketSoporte> toModel(@NonNull TicketSoporte ticket) {
        return EntityModel.of(ticket,
                linkTo(methodOn(TicketSoporteControladorV2.class).getTicketSoporteById(ticket.getIdTicket())).withSelfRel(),
                linkTo(methodOn(TicketSoporteControladorV2.class).getAllTicketSoportes()).withRel("tickets"));
    }
}
