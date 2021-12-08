package com.prueba.ronyreyna_inventarios.controller;

import com.prueba.ronyreyna_inventarios.models.entity.Producto;
import com.prueba.ronyreyna_inventarios.models.entity.Tienda;
import com.prueba.ronyreyna_inventarios.models.entity.Tienda_Productos;
import com.prueba.ronyreyna_inventarios.repository.TiendaProductoRepository;
import com.prueba.ronyreyna_inventarios.service.ProductoService;
import com.prueba.ronyreyna_inventarios.service.TiendaProductoService;
import com.prueba.ronyreyna_inventarios.service.TiendaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@RestController
@RequestMapping("/inventario/tiendaProducto")
public class TiendaProductoController {
    private final TiendaProductoService tiendaProductoService;
    private final TiendaService tiendaService;
    private final ProductoService productoService;

    @GetMapping(path = "/asigarTodosLosProductosporTienda/{codTienda}",produces = "application/json")
    private String asiganarTodosProductosPorTienda(@PathVariable("codTienda") String codTienda) {
        Tienda tienda = tiendaService.tiendaPorCod(codTienda);
        if (Objects.nonNull(tienda)) {
            productoService.listProd().forEach(p -> tiendaProductoService.asignarProductoTienda(tienda, p, true));
            return "se realizo la asigancion con exito";
        } else {
            return "No se encontro la tienda no se puede realizar la asigancion correctamente";
        }
    }

    @GetMapping(path = "/asigarUnProductoAUnaTienda/{codTienda}/{codProducto}",produces = "application/json")
    private String asiganarUnProductosPorTienda(@PathVariable("codTienda") String codTienda, @PathVariable("codProducto") String codProducto) {
        Tienda tienda = tiendaService.tiendaPorCod(codTienda);
        Producto producto = productoService.buscarUnoCod(codProducto);
        if (Objects.nonNull(tienda)) {
            if (Objects.nonNull(producto)) {
                tiendaProductoService.asignarProductoTienda(tienda, producto, true);
                return "se realizo la asigancion con exito";
            } else {
                return "No se encontro el producto no se puede realizar la asigancion correctamente";
            }
        } else {
            return "No se encontro la tienda no se puede realizar la asigancion correctamente";
        }
    }

    @GetMapping(path = "/quitarTodosLosProductosporTienda/{codTienda}",produces = "application/json")
    private String quitarTodosProductosPorTienda(@PathVariable("codTienda") String codTienda) {
        Tienda tienda = tiendaService.tiendaPorCod(codTienda);
        if (Objects.nonNull(tienda)) {
            productoService.listProd().forEach(p -> tiendaProductoService.asignarProductoTienda(tienda, p, false));
            return "se realizo la asigancion con exito";
        } else {
            return "No se encontro la tienda no se puede realizar la asigancion correctamente";
        }
    }

    @GetMapping(path = "/quitarUnProductoAUnaTienda/{codTienda}/{codProducto}",produces = "application/json")
    private String quitarUnProductosPorTienda(@PathVariable("codTienda") String codTienda, @PathVariable("codProducto") String codProducto) {
        Tienda tienda = tiendaService.tiendaPorCod(codTienda);
        Producto producto = productoService.buscarUnoCod(codProducto);
        if (Objects.nonNull(tienda)) {
            if (Objects.nonNull(producto)) {
                tiendaProductoService.asignarProductoTienda(tienda, producto, false);
                return "se quito con exito";
            } else {
                return "No se encontro el producto no se puede quitar correctamente";
            }
        } else {
            return "No se encontro la tienda no se puede quitar correctamente";
        }
    }


}
