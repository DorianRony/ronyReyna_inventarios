package com.prueba.ronyreyna_inventarios.service;

import com.prueba.ronyreyna_inventarios.models.entity.Tienda;
import com.prueba.ronyreyna_inventarios.repository.ClienteRepository;
import com.prueba.ronyreyna_inventarios.repository.TiendaProductoRepository;
import com.prueba.ronyreyna_inventarios.repository.TiendaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TiendaService {
    private final TiendaRepository tiendaRepository;

    public Tienda tiendaPorId(int id){
        return tiendaRepository.findById(id).get();
    }

    public Tienda tiendaPorCod(String cod){
        return tiendaRepository.findByCod(cod);
    }

    public Tienda tiendaPorNombre(String name){
        return tiendaRepository.findByCod(name);
    }
}
