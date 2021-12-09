package com.prueba.ronyreyna_inventarios.controller;

import com.prueba.ronyreyna_inventarios.models.entity.Tienda;
import com.prueba.ronyreyna_inventarios.service.TiendaService;
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

@WebMvcTest(TiendaController.class)
class TiendaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TiendaService tiendaService;

    @Test
    void buscarUnoCod() throws Exception {
        Tienda tienda = new Tienda();
        when(tiendaService.tiendaPorCod("tienda-1")).thenReturn(tienda);
        this.mockMvc.perform(get("/inventario/tienda/buscarUnoCod/tienda-1")).andExpect(status().isOk());
    }

    @Test
    void listar() throws Exception {
        List<Tienda> tiendas = new LinkedList<>();
        when(tiendaService.listar()).thenReturn(tiendas);
        this.mockMvc.perform(get("/inventario/tienda/listar")).andExpect(status().isOk());
    }

    @Test
    void buscarUnoName() throws Exception {
        Tienda tienda = new Tienda();
        when(tiendaService.tiendaPorCod("tienda-nombre-1")).thenReturn(tienda);
        this.mockMvc.perform(get("/inventario/tienda/buscarUnoCod/tienda-nombre-1")).andExpect(status().isOk());
    }
}