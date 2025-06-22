package Logistica.logistica;

import Logistica.logistica.Modelo.Envio;
import Logistica.logistica.Repositorio.EnvioRepositorio;
import Logistica.logistica.Servicio.EnvioServicio;
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

class EnvioServicioTest {
    @Mock
    private EnvioRepositorio envioRepositorio;
    @InjectMocks
    private EnvioServicio envioServicio;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testListarEnvios() {
        Envio envio = new Envio();
        when(envioRepositorio.findAll()).thenReturn(Arrays.asList(envio));
        List<Envio> result = envioServicio.listarEnvios();
        assertEquals(1, result.size());
    }

    @Test
    void testObtenerEnvioPorId() {
        Envio envio = new Envio();
        when(envioRepositorio.findById(1)).thenReturn(Optional.of(envio));
        Optional<Envio> result = envioServicio.obtenerEnvioPorId(1);
        assertTrue(result.isPresent());
    }

    @Test
    void testGuardarEnvio() {
        Envio envio = new Envio();
        when(envioRepositorio.save(envio)).thenReturn(envio);
        Envio result = envioServicio.guardarEnvio(envio);
        assertNotNull(result);
    }

    @Test
    void testEliminarEnvio() {
        doNothing().when(envioRepositorio).deleteById(1);
        envioServicio.eliminarEnvio(1);
        verify(envioRepositorio, times(1)).deleteById(1);
    }
}
