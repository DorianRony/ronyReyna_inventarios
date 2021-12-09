package com.prueba.ronyreyna_inventarios.controller;

import com.prueba.ronyreyna_inventarios.models.DTO.ProductoCodName;
import com.prueba.ronyreyna_inventarios.models.entity.Producto;
import com.prueba.ronyreyna_inventarios.service.ProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@RestController
@RequestMapping("/inventario/producto")
public class ProductoController {
    private final ProductoService productoService;

    @PostMapping(path = "/guardar", produces = "application/json")
    public Producto guardar(@RequestBody @Validated Producto producto) {
        return productoService.save(producto);
    }

    @PostMapping(path = "/actualizar", produces = "application/json")
    public void actualizar(@RequestBody @Validated Producto producto) {
        productoService.save(producto);
    }

    @GetMapping(path = "/buscarUnoPorCod/{codProducto}", produces = "application/json")
    public Producto buscarUnoCod(@PathVariable("codProducto") String cod) {
        return productoService.buscarUnoCod(cod);
    }

    @GetMapping(path = "/listarProdCodName", produces = "application/json")
    public List<ProductoCodName> listaProductosCodName() {
        return productoService.listProdCodName();
    }

    @GetMapping(path = "/listarProd", produces = "application/json")
    public List<Producto> listaProductos() {
        return productoService.listProd();
    }

    @GetMapping(path = "/actualizarStock/{codProducto}/{stock}", produces = "application/json")
    public String actualizarStock(@PathVariable("codProducto") String cod, @PathVariable("stock") Integer stock) {
        Producto producto = buscarUnoCod(cod);
        if (Objects.nonNull(producto)) {
            if (Objects.nonNull(stock) && stock > 0) {
                producto.setStock(stock);
                actualizar(producto);
            } else {
                return "Error: no se actualizo el stock, stock enviado no es correcto";
            }
            return "Stock actulizado con exito";
        } else {
            return "Error: no se encuentra el codigo solicitado";
        }
    }
}
