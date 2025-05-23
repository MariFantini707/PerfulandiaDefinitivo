package Logistica.logistica.Modelo;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "envio")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Envio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEnvio;

    @Column(nullable = false)
    private LocalDate fechaEnvio;

    @Column(nullable = false)
    private String estadoEnvio;

    @Column(nullable = false)
    private String origen;
    
}
