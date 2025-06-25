package Operaciones.operaciones;

import Operaciones.operaciones.modelo.Resena;
import Operaciones.operaciones.repositorio.ResenaRepositorio;
import Operaciones.operaciones.servicio.ResenaServicio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ResenaServicioTest {
    @Mock
    private ResenaRepositorio resenaRepositorio;
    @InjectMocks
    private ResenaServicio resenaServicio;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllResenas() {
        Resena resena = new Resena();
        when(resenaRepositorio.findAll()).thenReturn(Arrays.asList(resena));
        List<Resena> result = resenaServicio.getAllResenas();
        assertEquals(1, result.size());
    }

    @Test
    void testGetResenaById() {
        Resena resena = new Resena();
        when(resenaRepositorio.findById(1)).thenReturn(Optional.of(resena));
        Optional<Resena> result = resenaServicio.getResenaById(1);
        assertTrue(result.isPresent());
    }

    @Test
    void testCreateResena() {
        Resena resena = new Resena();
        when(resenaRepositorio.save(resena)).thenReturn(resena);
        Resena result = resenaServicio.createResena(resena);
        assertNotNull(result);
    }

    @Test
    void testUpdateResena() {
        Resena resena = new Resena();
        resena.setComentarioResena("Muy bueno");
        resena.setPuntuacionResena(5);
        resena.setIdUsuario(2);
        when(resenaRepositorio.findById(1)).thenReturn(Optional.of(resena));
        when(resenaRepositorio.save(any(Resena.class))).thenReturn(resena);
        Resena result = resenaServicio.updateResena(1, resena);
        assertNotNull(result);
        assertEquals("Muy bueno", result.getComentarioResena());
        assertEquals(5, result.getPuntuacionResena());
        assertEquals(2, result.getIdUsuario());
    }

    @Test
    void testDeleteResena() {
        doNothing().when(resenaRepositorio).deleteById(1);
        resenaServicio.deleteResena(1);
        verify(resenaRepositorio, times(1)).deleteById(1);
    }
}
