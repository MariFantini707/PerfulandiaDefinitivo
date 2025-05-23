package Operaciones.operaciones.modelo;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
//import jakarta.persistence.JoinColumn;
//import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Resena {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer idResena;

    @Column(nullable = true)
    private String comentarioResena;
   
    @Column(nullable = false)
    private Integer puntuacionResena;
    // Si es que supiera como se hace la relacion con el usuario:
    //@ManyToOne
    //@JoinColumn(name = "idUsuario", nullable = false)
    // Y como no sé:
    @Column(nullable = false)
    @JsonProperty("idUsuario")
    // @JsonProperty("idUsuario") es para que el nombre de la propiedad en el JSON sea "idUsuario"
    private Integer idUsuario;
}
