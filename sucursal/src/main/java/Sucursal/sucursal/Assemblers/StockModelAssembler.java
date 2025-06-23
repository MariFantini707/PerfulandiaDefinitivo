package Sucursal.sucursal.Assemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import Sucursal.sucursal.Controlador.StockControladorV2;
import Sucursal.sucursal.Modelo.Stock;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
public class StockModelAssembler implements RepresentationModelAssembler<Stock, EntityModel<Stock>> {
    @Override
    public @NonNull EntityModel<Stock> toModel(@NonNull Stock stock) {
        return EntityModel.of(stock,
                linkTo(methodOn(StockControladorV2.class).getStockById(stock.getIdStock())).withSelfRel(),
                linkTo(methodOn(StockControladorV2.class).getAllStocks()).withRel("stocks"));
    }
}
