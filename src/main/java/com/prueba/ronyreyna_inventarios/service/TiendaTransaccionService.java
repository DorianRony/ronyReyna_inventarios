package com.prueba.ronyreyna_inventarios.service;

import com.prueba.ronyreyna_inventarios.models.entity.Cliente;
import com.prueba.ronyreyna_inventarios.models.entity.Producto;
import com.prueba.ronyreyna_inventarios.models.entity.Tienda;
import com.prueba.ronyreyna_inventarios.models.entity.Tienda_Transacciones;
import com.prueba.ronyreyna_inventarios.repository.ClienteRepository;
import com.prueba.ronyreyna_inventarios.repository.TiendaTransaccionRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@RequiredArgsConstructor
public class TiendaTransaccionService {
    private final TiendaTransaccionRepository tiendaTransaccionRepository;
    private final ClienteService clienteService;

    public void registoMovimientoTienda(Cliente cliente, Tienda tienda, Producto producto, Integer cantidad) {
        Tienda_Transacciones tienda_transacciones = new Tienda_Transacciones();
        tienda_transacciones.setTienda(tienda);
        tienda_transacciones.setProducto(producto);
        tienda_transacciones.setCliente(cliente);
        tienda_transacciones.setCantidad(cantidad);
        tiendaTransaccionRepository.save(tienda_transacciones);
    }

    public List<Tienda_Transacciones> tiendaTransacciones() {
        return (List<Tienda_Transacciones>) tiendaTransaccionRepository.findAll();
    }

    public ByteArrayInputStream transaccionesClienteFechas(String cliente, long i, long f) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        List<List<String>> dataFull = new LinkedList<>();
        Cliente cliente1 = clienteService.findbyIdentificacion(cliente);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyy");

        //Use esta url para probar la descarga correcta http://localhost:8060/inventario/transacciones/reporteCvsTransacciones/0850270133/1639002146698/1639002146698
        if (Objects.nonNull(cliente1)) {
            Date incio = new Date(i);
            Date fin = new Date(f);
            if (incio.equals(fin) || incio.before(fin)) {
                List<Tienda_Transacciones> tienda_transacciones = tiendaTransaccionRepository.transaccionesClienteFechas(cliente1, incio, fin);
                tienda_transacciones.forEach(t -> {
                    List<String> data = Arrays.asList(
                            t.getCliente().getIdentificacion(),
                            t.getCliente().getName(),
                            sdf.format(t.getFecha()),
                            t.getTienda().getCod(),
                            t.getProducto().getCod(),
                            t.getCantidad().toString());
                    dataFull.add(data);
                });
            } else {
                List<String> data = Collections.singletonList("Las fechas no tienen un formato correcto");
                dataFull.add(data);
            }
        } else {
            List<String> data = Collections.singletonList("El cliente que solicito no existe en la BD");
            dataFull.add(data);
        }

        return new ByteArrayInputStream(generateCvs(dataFull, out).toByteArray());
    }

    private ByteArrayOutputStream generateCvs(List<List<String>> dataFull, ByteArrayOutputStream out) throws IOException {
        CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out), CSVFormat.DEFAULT);

        dataFull.forEach(t -> {
            try {
                csvPrinter.printRecord(t);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        csvPrinter.flush();
        return out;
    }

    public List<Tienda_Transacciones> lista() {
        return (List<Tienda_Transacciones>) tiendaTransaccionRepository.findAll();
    }
}
