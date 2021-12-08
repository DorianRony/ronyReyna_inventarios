package com.prueba.ronyreyna_inventarios.controller;

import com.prueba.ronyreyna_inventarios.models.entity.Tienda;
import com.prueba.ronyreyna_inventarios.service.TiendaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/inventario/tienda")
public class TiendaController {
    private final TiendaService tiendaService;

    @GetMapping(path = "/buscarUnoID/{id}",produces = "application/json")
    public Tienda buscarUnoID(@PathVariable("id") int id){
        return tiendaService.tiendaPorId(id);
    }

    @GetMapping(path = "/buscarUnoCod/{cod}",produces = "application/json")
    public Tienda buscarUnoCod(@PathVariable("cod") String cod){
        return tiendaService.tiendaPorCod(cod);
    }

    @GetMapping(path = "/buscarUnoName/{name}",produces = "application/json")
    public Tienda buscarUnoName(@PathVariable("name") String name){
        return tiendaService.tiendaPorNombre(name);
    }
}
