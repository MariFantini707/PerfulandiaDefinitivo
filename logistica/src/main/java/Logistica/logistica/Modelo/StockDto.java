package Logistica.logistica.Modelo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO que representa el stock disponible de un producto")
public class StockDto {

    @Schema(description = "Identificador único del registro de stock", example = "501")
    private Integer idStock;

    @Schema(description = "Cantidad disponible del producto en stock", example = "120")
    private Integer cantidad;

    @Schema(description = "Identificador del producto asociado al stock", example = "101")
    private Integer idProducto;
}
