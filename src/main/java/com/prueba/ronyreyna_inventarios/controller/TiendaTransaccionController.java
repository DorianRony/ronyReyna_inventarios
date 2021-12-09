package com.prueba.ronyreyna_inventarios.controller;

import com.prueba.ronyreyna_inventarios.models.entity.Tienda_Transacciones;
import com.prueba.ronyreyna_inventarios.service.TiendaTransaccionService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/inventario/transacciones")
public class TiendaTransaccionController {

    private final TiendaTransaccionService tiendaTransaccionService;

    @GetMapping(path = "/reporteCvsTransacciones/{cliente}/{fechaInicio}/{fechaFin}")
    private ResponseEntity<Resource> cvsReport(@PathVariable("cliente") String codCliente,
                                               @PathVariable("fechaInicio") long fechaInicio,
                                               @PathVariable("fechaFin") long fechaFin) throws IOException {
        String filename = "reporteCliente"+codCliente+".csv";
        InputStreamResource file = new InputStreamResource(tiendaTransaccionService.transaccionesClienteFechas(codCliente,fechaInicio,fechaFin));

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
                .contentType(MediaType.parseMediaType("application/csv"))
                .body(file);
    }

    @GetMapping(path = "/listar", produces = "application/json")
    public List<Tienda_Transacciones> lista() {
        return tiendaTransaccionService.lista();
    }

}
