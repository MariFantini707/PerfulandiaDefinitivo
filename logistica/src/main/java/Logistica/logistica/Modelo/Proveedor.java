package Logistica.logistica.Modelo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "proveedor")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Entidad que representa a un proveedor")
public class Proveedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador único del proveedor", example = "200")
    private Integer idProveedor;

    @Column(length = 150, nullable = false)
    @Schema(description = "Nombre del proveedor", example = "Tech Global S.A.")
    private String nombreProveedor;

    @Column(nullable = false)
    @Schema(description = "Teléfono de contacto del proveedor", example = "987654321")
    private Integer telefonoProveedor;

    @Column(length = 100, nullable = false)
    @Schema(description = "Correo electrónico del proveedor", example = "contacto@techglobal.cl")
    private String correoProveedor;

    @Column(length = 150, nullable = false)
    @Schema(description = "Dirección del proveedor", example = "Av. Apoquindo 1234, Las Condes, Santiago")
    private String direccionProveedor;
}


//el localhost es: http://localhost:8082/proveedores
