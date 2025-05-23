package Logistica.logistica.Modelo;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class ProductoDto {
    @JsonProperty("idProducto")
    private Integer idProducto;

    @JsonProperty("nombreProducto")
    private String nombreProducto;

    @JsonProperty("precioProducto")
    private Integer precioProducto;

    @JsonProperty("categoriaProducto")
    private String categoriaProducto;
}
