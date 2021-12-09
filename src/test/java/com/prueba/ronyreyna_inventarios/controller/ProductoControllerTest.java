package com.prueba.ronyreyna_inventarios.controller;

import com.prueba.ronyreyna_inventarios.models.DTO.ProductoCodName;
import com.prueba.ronyreyna_inventarios.models.entity.Producto;
import com.prueba.ronyreyna_inventarios.service.ProductoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.LinkedList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductoController.class)
class ProductoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductoService productoService;

    @Test
    void buscarUnoCod() throws Exception {
        Producto producto = new Producto();
        when(productoService.buscarUnoCod("prod-1")).thenReturn(producto);
        this.mockMvc.perform(get("/inventario/producto/buscarUnoPorCod/prod-1")).andExpect(status().isOk());
    }

    @Test
    void listaProductosCodName() throws Exception {
        List<ProductoCodName> productos = new LinkedList<>();
        when(productoService.listProdCodName()).thenReturn(productos);
        this.mockMvc.perform(get("/inventario/producto/listarProdCodName")).andExpect(status().isOk());
    }

    @Test
    void listaProductos() throws Exception {
        List<Producto> productos = new LinkedList<>();
        when(productoService.listProd()).thenReturn(productos);
        this.mockMvc.perform(get("/inventario/producto/listarProd")).andExpect(status().isOk());
    }
}