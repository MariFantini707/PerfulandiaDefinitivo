package Operaciones.operaciones;

import Operaciones.operaciones.modelo.Resena;
import Operaciones.operaciones.servicio.ResenaServicio;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ResenaControladorTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ResenaServicio resenaServicio;

    @Autowired
    private ObjectMapper objectMapper;

    private Resena resena;

    @BeforeEach
    void setUp() {
        resena = new Resena();
        resena.setIdResena(1);
        resena.setComentarioResena("Excelente atención");
        resena.setPuntuacionResena(5);
        resena.setIdUsuario(7);
    }

    @Test
    void testGetResenas() throws Exception {
        when(resenaServicio.getAllResenas()).thenReturn(Collections.singletonList(resena));
        mockMvc.perform(get("/api/v1/resenas"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].idResena").value(resena.getIdResena()))
            .andExpect(jsonPath("$[0].comentarioResena").value(resena.getComentarioResena()))
            .andExpect(jsonPath("$[0].puntuacionResena").value(resena.getPuntuacionResena()))
            .andExpect(jsonPath("$[0].idUsuario").value(resena.getIdUsuario()));
    }

    @Test
    void testGetResenaById() throws Exception {
        when(resenaServicio.getResenaById(1)).thenReturn(java.util.Optional.of(resena));
        mockMvc.perform(get("/api/v1/resenas/1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.idResena").value(resena.getIdResena()))
            .andExpect(jsonPath("$.comentarioResena").value(resena.getComentarioResena()))
            .andExpect(jsonPath("$.puntuacionResena").value(resena.getPuntuacionResena()))
            .andExpect(jsonPath("$.idUsuario").value(resena.getIdUsuario()));
    }

    @Test
    void testPostResena() throws Exception {
        when(resenaServicio.createResena(any(Resena.class))).thenReturn(resena);
        mockMvc.perform(post("/api/v1/resenas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(resena)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.idResena").value(resena.getIdResena()))
            .andExpect(jsonPath("$.comentarioResena").value(resena.getComentarioResena()))
            .andExpect(jsonPath("$.puntuacionResena").value(resena.getPuntuacionResena()))
            .andExpect(jsonPath("$.idUsuario").value(resena.getIdUsuario()));
    }

    @Test
    void testPutResena() throws Exception {
        when(resenaServicio.updateResena(eq(1), any(Resena.class))).thenReturn(resena);
        mockMvc.perform(put("/api/v1/resenas/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(resena)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.idResena").value(resena.getIdResena()))
            .andExpect(jsonPath("$.comentarioResena").value(resena.getComentarioResena()))
            .andExpect(jsonPath("$.puntuacionResena").value(resena.getPuntuacionResena()))
            .andExpect(jsonPath("$.idUsuario").value(resena.getIdUsuario()));
    }

    @Test
    void testDeleteResena() throws Exception {
        doNothing().when(resenaServicio).deleteResena(1);
        mockMvc.perform(delete("/api/v1/resenas/1"))
            .andExpect(status().isOk());
    }
}
