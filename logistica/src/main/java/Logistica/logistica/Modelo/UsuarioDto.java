package Logistica.logistica.Modelo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class UsuarioDto {
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("nombreUsuario")
    private String nombreUsuario;
    @JsonProperty("rutUsuario")
    private String rutUsuario;
    @JsonProperty("correoUsuario")
    private String correoUsuario;
    @JsonProperty("rolUsuario")
    private String rolUsuario;
}
