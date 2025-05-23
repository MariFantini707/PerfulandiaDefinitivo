package Logistica.logistica.Modelo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StockDto {
    private Integer idStock;
    private Integer cantidad;
    private Integer idProducto;
}
