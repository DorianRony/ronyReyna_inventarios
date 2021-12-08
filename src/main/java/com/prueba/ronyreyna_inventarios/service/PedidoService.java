package com.prueba.ronyreyna_inventarios.service;

import com.prueba.ronyreyna_inventarios.models.DTO.DetallePedidoCliente;
import com.prueba.ronyreyna_inventarios.models.DTO.PedidoCliente;
import com.prueba.ronyreyna_inventarios.models.entity.*;
import com.prueba.ronyreyna_inventarios.repository.ClienteRepository;
import com.prueba.ronyreyna_inventarios.repository.PedidoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class PedidoService {
    private final PedidoRepository pedidoRepository;
    private final ClienteService clienteService;
    private final TiendaService tiendaService;
    private final TiendaProductoService tiendaProductoService;
    private final TiendaTransaccionService tiendaTransaccionService;
    private final DetallePedidoService detallePedidoService;
    private final ProductoService productoService;

    public Pedido save(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }

    public String codPedido() {
        Integer ultimoPedido = pedidoRepository.ultimoPedido();
        if (Objects.nonNull(ultimoPedido)) {
            return "Pedido-" + ++ultimoPedido;
        } else {
            return "Pedido-1";
        }
    }

    public PedidoCliente realizarPedido(PedidoCliente pedidoCliente) {
        StringBuilder stringBuilder = new StringBuilder();
        Cliente cliente = clienteService.findbyIdentificacion(pedidoCliente.getIdentificacionCLiente());
        if (Objects.nonNull(cliente)) {
            Pedido pedido = new Pedido();
            pedido.setCliente(cliente);
            pedido.setCodPedido(codPedido());
            pedido = save(pedido);
            pedidoCliente.setIdPedido(pedido.getId());
            Pedido finalPedido = pedido;
            pedidoCliente.getDetallePedidos().forEach(dp -> {
                Tienda tienda = tiendaService.tiendaPorCod(dp.getCodTienda());
                Producto producto = productoService.buscarUnoCod(dp.getCodProducto());
                if (Objects.nonNull(tienda) && Objects.nonNull(producto)) {
                    Tienda_Productos byTiendaAndProducto = tiendaProductoService.findByTiendaAndProducto(tienda, producto);
                    if (Objects.nonNull(byTiendaAndProducto)) {
                        DetallePedido detallePedido = new DetallePedido();
                        detallePedido.setPedido(finalPedido);
                        detallePedido.setCantidad(dp.getCantidad());
                        detallePedido.setTiendaProductos(byTiendaAndProducto);
                        detallePedidoService.save(detallePedido);

                        tiendaTransaccionService.registoMovimientoTienda(cliente, tienda, producto, dp.getCantidad());
                    } else {
                        stringBuilder.append("no se encuentra asigando el producto a la tienda no se agrego al pedido");
                    }
                } else {
                    stringBuilder.append("no se encuentra la tienda o el producto no se agrego al pedido");
                }
            });
        } else {
            stringBuilder.append("no se encuentra el cliente no se pudo realizar el pedido");
        }

        pedidoCliente.setMensaje(stringBuilder.toString());
        return pedidoCliente;
    }

    private PedidoCliente validacionPedido(PedidoCliente pedidoCliente) {
        StringBuilder stringBuilder = new StringBuilder();
        Cliente cliente = clienteService.findbyIdentificacion(pedidoCliente.getIdentificacionCLiente());
        if (Objects.isNull(cliente)) {
            stringBuilder.append("Pedido rechazado error: no existe el cliente");
        } else {
            pedidoCliente.setCliente(cliente);
        }

        pedidoCliente.getDetallePedidos().forEach(dp -> {
            Tienda tienda = tiendaService.tiendaPorCod(dp.getCodTienda());
            if (Objects.isNull(tienda)) {
                stringBuilder.append("Pedido rechazado error: no existe la tienda Cod ").append(dp.getCodTienda());
            }

            Producto producto = productoService.buscarUnoCod(dp.getCodProducto());
            if (Objects.isNull(producto)) {
                stringBuilder.append("Pedido rechazado error: no existe el producto Cod ").append(dp.getCodProducto());
            }


            Tienda_Productos byTiendaAndProducto = tiendaProductoService.findByTiendaAndProducto(tienda, producto);
            if (Objects.isNull(byTiendaAndProducto)) {
                stringBuilder.append("Pedido rechazado error: el producto Cod ")
                        .append(dp.getCodProducto())
                        .append(" no esta asignado a la tienda Cod ")
                        .append(dp.getCodTienda());
            } else {
                dp.setTiendaProductos(byTiendaAndProducto);
                validarCantidadProductos(dp, stringBuilder);
            }
        });

        pedidoCliente.setMensaje(stringBuilder.toString());
        return pedidoCliente;
    }

    /*Si un cliente realiza el pedido de un producto y no se encuentra en stock, se debe realizar lo
    siguiente:
    1) Si el stock faltante es por mas de 10 unidades, se rechaza la transacción indicada el
    error: “Unidades no disponibles (> 10)”
    2) Si el stock faltante es de: (>5 unidades y <=10 unidades), se debe solicitar stock extra,
    para esto se simulará dicha petición haciendo uso de un servicio Mock (GET) el cual
    debe responder lo siguiente*/
    private void validarCantidadProductos(DetallePedidoCliente detallePedido, StringBuilder stringBuilder) {
        Integer stock = detallePedido.getTiendaProductos().getProducto().getStock();
        Integer cantidad = detallePedido.getCantidad();
        int faltante = (stock - cantidad) * -1;
        if (faltante > 10){
            stringBuilder.append("error: Unidades no disponibles (> 10)");
        }else if(faltante > 5){

        }
    }

}
