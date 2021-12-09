package com.prueba.ronyreyna_inventarios.service;

import com.prueba.ronyreyna_inventarios.models.DTO.ProductoCodName;
import com.prueba.ronyreyna_inventarios.models.entity.Producto;
import com.prueba.ronyreyna_inventarios.repository.ProductoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductoService {
    private final ProductoRepository productoRepository;

    public Producto save(Producto producto) {
        return productoRepository.save(producto);
    }

    public Producto buscarUnoCod(String cod) {
        return productoRepository.findByCod(cod);
    }

    public List<Producto> listProd() {
        return (List<Producto>) productoRepository.findAll();
    }

    public List<ProductoCodName> listProdCodName() {
        List<Producto> prods = (List<Producto>) productoRepository.findAll();
        return prods
                .stream()
                .map(p -> ProductoCodName.builder()
                        .cod(p.getCod())
                        .name(p.getName())
                        .build())
                .collect(Collectors.toList());
    }

    public BigDecimal costoProdPoCod(String cod){
        return productoRepository.costoProdPorCod(cod);
    }
}
