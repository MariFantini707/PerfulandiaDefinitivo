package Administracion.administracion;

import Administracion.administracion.Modelo.Usuario;
import Administracion.administracion.Repositorio.UsuarioRepositorio;
import Administracion.administracion.Servicio.UsuarioServicio;
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

class UsuarioServicioTest {
    @Mock
    private UsuarioRepositorio usuarioRepositorio;
    @InjectMocks
    private UsuarioServicio usuarioServicio;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllUsuarios() {
        Usuario usuario = new Usuario();
        when(usuarioRepositorio.findAll()).thenReturn(Arrays.asList(usuario));
        List<Usuario> result = usuarioServicio.findAll();
        assertEquals(1, result.size());
    }

    @Test
    void testGetUsuarioById() {
        Usuario usuario = new Usuario();
        when(usuarioRepositorio.findById(1)).thenReturn(Optional.of(usuario));
        Optional<Usuario> result = usuarioServicio.findById(1);
        assertTrue(result.isPresent());
    }

    @Test
    void testCreateUsuario() {
        Usuario usuario = new Usuario();
        when(usuarioRepositorio.save(usuario)).thenReturn(usuario);
        Usuario result = usuarioRepositorio.save(usuario);
        assertNotNull(result);
    }

    @Test
    void testDeleteUsuario() {
        doNothing().when(usuarioRepositorio).deleteById(1);
        usuarioRepositorio.deleteById(1);
        verify(usuarioRepositorio, times(1)).deleteById(1);
    }
}
