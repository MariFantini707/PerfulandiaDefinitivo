package Operaciones.operaciones.modelo;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UsuarioDto {
    @Column(name="id")
    private Integer id;
    @Column(name="nombreUsuario")
    private String nombreUsuario;
    @Column(name="rutUsuario")
    private String rutUsuario;
    @Column(name="correoUsuario")
    private String correoUsuario;
    @Column(name="rolUsuario")
    private String rolUsuario;
}
