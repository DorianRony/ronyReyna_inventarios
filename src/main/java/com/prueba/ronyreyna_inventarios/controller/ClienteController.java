package com.prueba.ronyreyna_inventarios.controller;

import com.prueba.ronyreyna_inventarios.models.entity.Cliente;
import com.prueba.ronyreyna_inventarios.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/inventario/cliente")
public class ClienteController {
    private final ClienteService clienteService;

    @GetMapping(path = "/listar", produces = "application/json")
    public List<Cliente> lista() {
        return clienteService.lista();
    }

    @GetMapping(path = "/buscarUnoIdentificacion/{identificacion}", produces = "application/json")
    public Cliente buscarUnIdentificacion(@PathVariable("identificacion") String identificacion) {
        return clienteService.findbyIdentificacion(identificacion);
    }

    @PostMapping(path = "/guardar", produces = "application/json")
    public Cliente guardar(@RequestBody @Validated Cliente cliente) {
        return clienteService.save(cliente);
    }

    @PutMapping(path = "/actualizar", produces = "application/json")
    public void actualizar(@RequestBody @Validated Cliente cliente) {
        clienteService.save(cliente);
    }

    @DeleteMapping(path = "/eliminar", produces = "application/json")
    public void delete(@RequestBody @Validated Cliente cliente) {
        clienteService.delete(cliente);
    }
}
