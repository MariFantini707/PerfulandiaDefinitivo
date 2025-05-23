package Operaciones.operaciones.modelo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UsuarioDto {
    @JsonProperty("idUsuario")
    private Integer idUsuario;
    @JsonProperty("nombreUsuario")
    private String nombreUsuario;
    @JsonProperty("rutUsuario")
    private String rutUsuario;
    @JsonProperty("correoUsuario")
    private String correoUsuario;
    @JsonProperty("rolUsuario")
    private String rolUsuario;
}
