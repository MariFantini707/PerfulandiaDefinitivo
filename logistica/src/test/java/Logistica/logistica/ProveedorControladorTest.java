package Logistica.logistica;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.jupiter.api.BeforeEach;
import Logistica.logistica.Modelo.Proveedor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import Logistica.logistica.Servicio.ProveedorServicio;
import org.springframework.http.MediaType;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.mockito.Mockito.doNothing;
import java.util.Collections;

@SpringBootTest
@AutoConfigureMockMvc
class ProveedorControladorTest {
    @Autowired
    private MockMvc mockMvc;

    private Proveedor proveedor;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private ProveedorServicio proveedorServicio;

    @BeforeEach
    void setUp() {
        proveedor = new Proveedor();
        proveedor.setIdProveedor(1);
        proveedor.setNombreProveedor("Proveedor Test");
        proveedor.setTelefonoProveedor(1234567);
        proveedor.setCorreoProveedor("proveedor@test.com");
        proveedor.setDireccionProveedor("Calle Falsa 123");
    }

    @Test
    void testGetProveedores() throws Exception {
        when(proveedorServicio.listarProveedores()).thenReturn(Collections.singletonList(proveedor));
        mockMvc.perform(get("/api/v1/proveedores"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].idProveedor").value(proveedor.getIdProveedor()))
            .andExpect(jsonPath("$[0].nombreProveedor").value(proveedor.getNombreProveedor()))
            .andExpect(jsonPath("$[0].telefonoProveedor").value(proveedor.getTelefonoProveedor()))
            .andExpect(jsonPath("$[0].correoProveedor").value(proveedor.getCorreoProveedor()))
            .andExpect(jsonPath("$[0].direccionProveedor").value(proveedor.getDireccionProveedor()));
    }
    @Test
    void testGetProveedorById() throws Exception {
        when(proveedorServicio.obtenerProveedorPorId(1)).thenReturn(java.util.Optional.of(proveedor));
        mockMvc.perform(get("/api/v1/proveedores/1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.idProveedor").value(proveedor.getIdProveedor()))
            .andExpect(jsonPath("$.nombreProveedor").value(proveedor.getNombreProveedor()))
            .andExpect(jsonPath("$.telefonoProveedor").value(proveedor.getTelefonoProveedor()))
            .andExpect(jsonPath("$.correoProveedor").value(proveedor.getCorreoProveedor()))
            .andExpect(jsonPath("$.direccionProveedor").value(proveedor.getDireccionProveedor()));
    }
    @Test
    void testPostProveedor() throws Exception {
        when(proveedorServicio.guardarProveedor(any(Proveedor.class))).thenReturn(proveedor);
        mockMvc.perform(post("/api/v1/proveedores")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(proveedor)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.idProveedor").value(proveedor.getIdProveedor()))
            .andExpect(jsonPath("$.nombreProveedor").value(proveedor.getNombreProveedor()))
            .andExpect(jsonPath("$.telefonoProveedor").value(proveedor.getTelefonoProveedor()))
            .andExpect(jsonPath("$.correoProveedor").value(proveedor.getCorreoProveedor()))
            .andExpect(jsonPath("$.direccionProveedor").value(proveedor.getDireccionProveedor()));
    }
    @Test
    void testPutProveedor() throws Exception {
        when(proveedorServicio.obtenerProveedorPorId(1)).thenReturn(java.util.Optional.of(proveedor));
        when(proveedorServicio.actualizarProveedor(any(Proveedor.class))).thenReturn(proveedor);
        mockMvc.perform(put("/api/v1/proveedores/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(proveedor)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.idProveedor").value(proveedor.getIdProveedor()))
            .andExpect(jsonPath("$.nombreProveedor").value(proveedor.getNombreProveedor()))
            .andExpect(jsonPath("$.telefonoProveedor").value(proveedor.getTelefonoProveedor()))
            .andExpect(jsonPath("$.correoProveedor").value(proveedor.getCorreoProveedor()))
            .andExpect(jsonPath("$.direccionProveedor").value(proveedor.getDireccionProveedor()));
    }
    @Test
    void testDeleteProveedor() throws Exception {
        when(proveedorServicio.obtenerProveedorPorId(1)).thenReturn(java.util.Optional.of(proveedor));
        doNothing().when(proveedorServicio).eliminarProveedor(1);
        mockMvc.perform(delete("/api/v1/proveedores/1"))
            .andExpect(status().isNoContent());
    }
}
