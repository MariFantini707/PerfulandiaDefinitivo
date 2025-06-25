package Sucursal.sucursal;

import Sucursal.sucursal.Modelo.Producto;
import Sucursal.sucursal.Servicio.ProductoServicio;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ProductoControladorTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ProductoServicio productoServicio;

    private Producto producto;
    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        producto = new Producto();
        producto.setIdProducto(1);
        producto.setNombreProducto("Test");
        producto.setPrecioProducto(1000);
        producto.setCategoriaProducto("Categoria");
        producto.setIdCarrito(1);
        // producto.setSucursal(...) and producto.setStock(...) can be set if needed
    }

    @Test
    void testGetProductos() throws Exception {
        when(productoServicio.getAllProductos()).thenReturn(Collections.singletonList(producto));
        mockMvc.perform(get("/api/v1/productos"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].idProducto").value(producto.getIdProducto()))
            .andExpect(jsonPath("$[0].nombreProducto").value(producto.getNombreProducto()))
            .andExpect(jsonPath("$[0].precioProducto").value(producto.getPrecioProducto()))
            .andExpect(jsonPath("$[0].categoriaProducto").value(producto.getCategoriaProducto()))
            .andExpect(jsonPath("$[0].idCarrito").value(producto.getIdCarrito()));
    }

    @Test
    void testGetProductoById() throws Exception {
        when(productoServicio.getProductoById(1)).thenReturn(java.util.Optional.of(producto));
        mockMvc.perform(get("/api/v1/productos/1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.idProducto").value(producto.getIdProducto()))
            .andExpect(jsonPath("$.nombreProducto").value(producto.getNombreProducto()))
            .andExpect(jsonPath("$.precioProducto").value(producto.getPrecioProducto()))
            .andExpect(jsonPath("$.categoriaProducto").value(producto.getCategoriaProducto()))
            .andExpect(jsonPath("$.idCarrito").value(producto.getIdCarrito()));
    }

    @Test
    void testPostProducto() throws Exception {
        when(productoServicio.createProducto(any(Producto.class))).thenReturn(producto);
        String json = objectMapper.writeValueAsString(producto);
        mockMvc.perform(post("/api/v1/productos").contentType("application/json").content(json))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.idProducto").value(producto.getIdProducto()))
            .andExpect(jsonPath("$.nombreProducto").value(producto.getNombreProducto()))
            .andExpect(jsonPath("$.precioProducto").value(producto.getPrecioProducto()))
            .andExpect(jsonPath("$.categoriaProducto").value(producto.getCategoriaProducto()))
            .andExpect(jsonPath("$.idCarrito").value(producto.getIdCarrito()));
    }

    @Test
    void testPutProducto() throws Exception {
        when(productoServicio.updateProducto(eq(1), any(Producto.class))).thenReturn(producto);
        String json = objectMapper.writeValueAsString(producto);
        mockMvc.perform(put("/api/v1/productos/1").contentType("application/json").content(json))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.idProducto").value(producto.getIdProducto()))
            .andExpect(jsonPath("$.nombreProducto").value(producto.getNombreProducto()))
            .andExpect(jsonPath("$.precioProducto").value(producto.getPrecioProducto()))
            .andExpect(jsonPath("$.categoriaProducto").value(producto.getCategoriaProducto()))
            .andExpect(jsonPath("$.idCarrito").value(producto.getIdCarrito()));
    }

    @Test
    void testDeleteProducto() throws Exception {
        doNothing().when(productoServicio).deleteProducto(1);
        mockMvc.perform(delete("/api/v1/productos/1"))
            .andExpect(status().isNoContent());
    }

    @Test
    void testAumentarStock() throws Exception {
        when(productoServicio.aumentarStock(1, 10)).thenReturn(producto);
        mockMvc.perform(put("/api/v1/productos/aumentarStock/1/10"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.idProducto").value(producto.getIdProducto()))
            .andExpect(jsonPath("$.nombreProducto").value(producto.getNombreProducto()))
            .andExpect(jsonPath("$.precioProducto").value(producto.getPrecioProducto()))
            .andExpect(jsonPath("$.categoriaProducto").value(producto.getCategoriaProducto()))
            .andExpect(jsonPath("$.idCarrito").value(producto.getIdCarrito()));
    }
}
