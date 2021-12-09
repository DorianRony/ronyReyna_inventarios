package com.prueba.ronyreyna_inventarios.service;

import com.prueba.ronyreyna_inventarios.controller.mock.ServiceMockController;
import com.prueba.ronyreyna_inventarios.models.DTO.DetallePedidoCliente;
import com.prueba.ronyreyna_inventarios.models.DTO.PedidoCliente;
import com.prueba.ronyreyna_inventarios.models.DTO.ProductoStock;
import com.prueba.ronyreyna_inventarios.models.entity.*;
import com.prueba.ronyreyna_inventarios.repository.ClienteRepository;
import com.prueba.ronyreyna_inventarios.repository.PedidoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

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
    private final ServiceMockController serviceMockController;

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
        validacionPedido(pedidoCliente);
        if (pedidoCliente.getMensaje().isEmpty()) {
            Pedido pedido = new Pedido();
            pedido.setCliente(pedidoCliente.getCliente());
            pedido.setCodPedido(codPedido());
            pedido = save(pedido);
            pedidoCliente.setIdPedido(pedido.getId());
            Pedido finalPedido = pedido;
            pedidoCliente.getDetallePedidos().forEach(dp -> {
                Tienda_Productos tiendaProductos = dp.getTiendaProductos();

                verificarAgregarStock(tiendaProductos.getProducto(), dp.getCantidad());
                restarStock(tiendaProductos.getProducto(), dp.getCantidad());

                DetallePedido detallePedido = new DetallePedido();
                detallePedido.setPedido(finalPedido);
                detallePedido.setCantidad(dp.getCantidad());
                detallePedido.setTiendaProductos(tiendaProductos);
                detallePedidoService.save(detallePedido);

                tiendaTransaccionService.registoMovimientoTienda(pedidoCliente.getCliente(), tiendaProductos.getTienda(), tiendaProductos.getProducto(), dp.getCantidad());
            });
        }

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
            }
        });

        boolean b = pedidoCliente.getDetallePedidos().stream().anyMatch(dp -> validarCantidadProductos(dp, stringBuilder));
        pedidoCliente.setMensaje(stringBuilder.toString());
        return pedidoCliente;
    }

    private boolean validarCantidadProductos(DetallePedidoCliente detallePedido, StringBuilder stringBuilder) {
        Producto producto = detallePedido.getTiendaProductos().getProducto();
        Integer stock = producto.getStock();
        Integer cantidad = detallePedido.getCantidad();
        int faltante = (stock - cantidad) * -1;
        if (faltante > 10) {
            stringBuilder.append("error: ").append("El producto Cod").append(detallePedido.getCodProducto()).append(" no tiene Unidades no disponibles (> 10)");
            return false;
        }
        return true;
    }

    private void verificarAgregarStock(Producto producto, Integer cantidad) {
        Integer stock = producto.getStock();
        int faltante = (stock - cantidad) * -1;
        if (faltante > 5) {
            Integer stockSolicitado10 = serviceMockController.getProductStock10().getStock();
            producto.setStock(stock + stockSolicitado10);
            productoService.save(producto);
        } else if (faltante >= 0) {
            Integer stockSolicitado5 = serviceMockController.getProductStock10().getStock();
            producto.setStock(stock + stockSolicitado5);
            productoService.save(producto);
        }
    }

    private void restarStock(Producto producto, Integer cantidad) {
        producto.setStock(producto.getStock()-cantidad);
        productoService.save(producto);
    }
}
