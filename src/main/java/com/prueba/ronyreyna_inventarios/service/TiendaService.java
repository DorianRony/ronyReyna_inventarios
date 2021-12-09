package com.prueba.ronyreyna_inventarios.service;

import com.prueba.ronyreyna_inventarios.models.DTO.TiendaNumeroTransacciones;
import com.prueba.ronyreyna_inventarios.models.DTO.TiendaProductoMonto;
import com.prueba.ronyreyna_inventarios.models.entity.Tienda;
import com.prueba.ronyreyna_inventarios.models.entity.Tienda_Transacciones;
import com.prueba.ronyreyna_inventarios.repository.TiendaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TiendaService {
    private final TiendaRepository tiendaRepository;
    private final TiendaTransaccionService tiendaTransaccionService;
    private final ProductoService productoService;

    public Tienda tiendaPorId(int id) {
        return tiendaRepository.findById(id).get();
    }

    public Tienda tiendaPorCod(String cod) {
        return tiendaRepository.findByCod(cod);
    }

    public Tienda tiendaPorNombre(String name) {
        return tiendaRepository.findByCod(name);
    }

    public List<TiendaNumeroTransacciones> tiendaNumeroTransacciones() {
        List<TiendaNumeroTransacciones> tiendaNumeroTransacciones = new LinkedList<>();
        Map<String, List<Tienda_Transacciones>> collect = tiendaTransaccionService.tiendaTransacciones()
                .stream()
                .collect(Collectors.groupingBy(t -> t.getTienda().getCod()));

        collect.forEach((tienda, lt) -> {
            Map<Date, List<Tienda_Transacciones>> collect1 = lt.stream().collect(Collectors.groupingBy(Tienda_Transacciones::getFecha));
            collect1.forEach((fecha, tran) -> {
                TiendaNumeroTransacciones build = TiendaNumeroTransacciones.builder()
                        .codTienda(tienda)
                        .fecha(fecha)
                        .numeroTransacciones(tran.size())
                        .build();
                tiendaNumeroTransacciones.add(build);
            });
        });

        return tiendaNumeroTransacciones;
    }

    public List<TiendaProductoMonto> tiendaMontoProducto() {
        List<TiendaProductoMonto> tiendaNumeroTransacciones = new LinkedList<>();
        Map<String, List<Tienda_Transacciones>> collect = tiendaTransaccionService.tiendaTransacciones()
                .stream()
                .collect(Collectors.groupingBy(t -> t.getTienda().getCod()));

        collect.forEach((tienda, lt) -> {
            Map<String, List<Tienda_Transacciones>> collect1 = lt.stream().collect(Collectors.groupingBy(t -> t.getProducto().getCod()));
            collect1.forEach((codProd, tran) -> {
                int sum = tran.stream().mapToInt(Tienda_Transacciones::getCantidad).sum();
                BigDecimal bigDecimal = productoService.costoProdPoCod(codProd);
                TiendaProductoMonto build = TiendaProductoMonto.builder()
                        .codTienda(tienda)
                        .codProducto(codProd)
                        .monto(bigDecimal.multiply(BigDecimal.valueOf(sum)))
                        .build();
                tiendaNumeroTransacciones.add(build);
            });
        });

        return tiendaNumeroTransacciones;
    }

    public List<Tienda> listar(){
        return (List<Tienda>) tiendaRepository.findAll();
    }
}
