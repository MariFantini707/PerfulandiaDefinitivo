package Logistica.logistica.Modelo;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO que representa información básica de un producto")
public class ProductoDto {

    @JsonProperty("idProducto")
    @Schema(description = "Identificador único del producto", example = "101")
    private Integer idProducto;

    @JsonProperty("nombreProducto")
    @Schema(description = "Nombre del producto", example = "Mouse inalámbrico")
    private String nombreProducto;

    @JsonProperty("precioProducto")
    @Schema(description = "Precio unitario del producto", example = "15990")
    private Integer precioProducto;

    @JsonProperty("categoriaProducto")
    @Schema(description = "Categoría del producto", example = "Accesorios de Computación")
    private String categoriaProducto;
}

