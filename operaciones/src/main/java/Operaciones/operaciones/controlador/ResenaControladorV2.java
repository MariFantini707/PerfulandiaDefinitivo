package Operaciones.operaciones.controlador;

import Operaciones.operaciones.modelo.Resena;
import Operaciones.operaciones.servicio.ResenaServicio;
import Operaciones.operaciones.Assemblers.ResenaModelAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/v2/resenas")
public class ResenaControladorV2 {
    @Autowired
    private ResenaServicio resenaServicio;
    @Autowired
    private ResenaModelAssembler assembler;

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<Resena>> getAllResenas() {
        List<EntityModel<Resena>> resenas = resenaServicio.getAllResenas().stream()
            .map(assembler::toModel)
            .collect(Collectors.toList());
        return CollectionModel.of(resenas,
            linkTo(methodOn(ResenaControladorV2.class).getAllResenas()).withSelfRel());
    }

    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public EntityModel<Resena> getResenaById(@PathVariable Integer id) {
        Resena resena = resenaServicio.getResenaById(id)
            .orElseThrow(() -> new RuntimeException("Reseña no encontrada"));
        return assembler.toModel(resena);
    }

    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Resena>> createResena(@RequestBody Resena resena) {
        Resena nuevaResena = resenaServicio.createResena(resena);
        return ResponseEntity
            .created(linkTo(methodOn(ResenaControladorV2.class).getResenaById(nuevaResena.getIdResena())).toUri())
            .body(assembler.toModel(nuevaResena));
    }

    @PutMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Resena>> updateResena(@PathVariable Integer id, @RequestBody Resena resena) {
        Resena updated = resenaServicio.updateResena(id, resena);
        return ResponseEntity.ok(assembler.toModel(updated));
    }

    @DeleteMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<?> deleteResena(@PathVariable Integer id) {
        resenaServicio.deleteResena(id);
        return ResponseEntity.noContent().build();
    }
}
