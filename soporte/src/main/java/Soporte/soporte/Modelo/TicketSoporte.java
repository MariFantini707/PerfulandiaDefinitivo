package Soporte.soporte.Modelo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Date;


import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
//import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
//import jakarta.persistence.JoinColumn;
//import jakarta.persistence.ManyToOne;
//import jakarta.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TicketSoporte {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer idTicket;

    @Column(nullable = false)
    private Integer descripcionTicket;
   
    @Column(nullable = false)
    private Integer estadoTicket;

    @Column(nullable = false)
    private Date fechaInicioTicket;
   
    @Column(nullable = true)
    private Date fechaTerminoTicket;

    @Column(nullable = false)
    private Integer respuestaTicket;


    @Column(nullable = false)
    @JsonProperty("idUsuario")
    private Integer idUsuario;
    

}
