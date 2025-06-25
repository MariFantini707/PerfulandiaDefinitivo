package Sucursal.sucursal;

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
import Sucursal.sucursal.Modelo.Sucursal;
import Sucursal.sucursal.Servicio.SucursalServicio;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import java.util.Collections;

@SpringBootTest
@AutoConfigureMockMvc
class SucursalControladorTest {
    @Autowired
    private MockMvc mockMvc;

    private Sucursal sucursal;

    @MockitoBean
    private SucursalServicio sucursalServicio;
    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        sucursal = new Sucursal();
        sucursal.setIdSucursal(1);
        sucursal.setNombreSucursal("Sucursal Plaza Oeste");
        sucursal.setDireccionSucursal("Av. Central 1234, Maipú, Santiago");
        sucursal.setTelefonoSucursal(227654321);
    }

    @Test
    void testGetSucursales() throws Exception {
        when(sucursalServicio.getAllSucursales()).thenReturn(Collections.singletonList(sucursal));
        mockMvc.perform(get("/api/v1/sucursales"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].idSucursal").value(sucursal.getIdSucursal()))
            .andExpect(jsonPath("$[0].nombreSucursal").value(sucursal.getNombreSucursal()))
            .andExpect(jsonPath("$[0].direccionSucursal").value(sucursal.getDireccionSucursal()))
            .andExpect(jsonPath("$[0].telefonoSucursal").value(sucursal.getTelefonoSucursal()));
    }
    @Test
    void testGetSucursalById() throws Exception {
        when(sucursalServicio.getSucursalById(1)).thenReturn(java.util.Optional.of(sucursal));
        mockMvc.perform(get("/api/v1/sucursales/1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.idSucursal").value(sucursal.getIdSucursal()))
            .andExpect(jsonPath("$.nombreSucursal").value(sucursal.getNombreSucursal()))
            .andExpect(jsonPath("$.direccionSucursal").value(sucursal.getDireccionSucursal()))
            .andExpect(jsonPath("$.telefonoSucursal").value(sucursal.getTelefonoSucursal()));
    }
    @Test
    void testPostSucursal() throws Exception {
        when(sucursalServicio.createSucursal(any(Sucursal.class))).thenReturn(sucursal);
        String json = objectMapper.writeValueAsString(sucursal);
        mockMvc.perform(post("/api/v1/sucursales").contentType("application/json").content(json))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.idSucursal").value(sucursal.getIdSucursal()))
            .andExpect(jsonPath("$.nombreSucursal").value(sucursal.getNombreSucursal()))
            .andExpect(jsonPath("$.direccionSucursal").value(sucursal.getDireccionSucursal()))
            .andExpect(jsonPath("$.telefonoSucursal").value(sucursal.getTelefonoSucursal()));
    }
    @Test
    void testPutSucursal() throws Exception {
        when(sucursalServicio.updateSucursal(eq(1), any(Sucursal.class))).thenReturn(sucursal);
        String json = objectMapper.writeValueAsString(sucursal);
        mockMvc.perform(put("/api/v1/sucursales/1").contentType("application/json").content(json))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.idSucursal").value(sucursal.getIdSucursal()))
            .andExpect(jsonPath("$.nombreSucursal").value(sucursal.getNombreSucursal()))
            .andExpect(jsonPath("$.direccionSucursal").value(sucursal.getDireccionSucursal()))
            .andExpect(jsonPath("$.telefonoSucursal").value(sucursal.getTelefonoSucursal()));
    }
    @Test
    void testDeleteSucursal() throws Exception {
        doNothing().when(sucursalServicio).deleteSucursal(1);
        mockMvc.perform(delete("/api/v1/sucursales/1"))
            .andExpect(status().isNoContent());
    }
}
