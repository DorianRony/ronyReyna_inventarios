package com.prueba.ronyreyna_inventarios.controller.mock;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.prueba.ronyreyna_inventarios.controller.ProductoController;
import com.prueba.ronyreyna_inventarios.models.DTO.ProductoStock;
import com.prueba.ronyreyna_inventarios.models.entity.Producto;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import lombok.RequiredArgsConstructor;

import java.util.List;

@ManagedBean
@RequiredArgsConstructor
public class ServiceMockController {
    private final Client c = Client.create();
    private WebResource webResource;
    private String json;
    private final Gson gson = new Gson();

    private final ProductoController productoController;

    @PostConstruct
    private void cargarProductos(){
        List<Producto> productMock = getProductMock();
        productMock.forEach(productoController::guardar);
    }

    public List<Producto> getProductMock() {
        webResource = c.resource("https://api.mocki.io/v2/50518e10/listaProductos");
        json = webResource.get(String.class);
        return gson.fromJson(json, new TypeToken<List<Producto>>() {
        }.getType());
    }

    public ProductoStock getProductStock10() {
        webResource = c.resource("https://api.mocki.io/v2/50518e10/stockAdicional10");
        json = webResource.get(String.class);
        return gson.fromJson(json, new TypeToken<ProductoStock>() {
        }.getType());
    }

    public ProductoStock getProductStock5() {
        webResource = c.resource("https://api.mocki.io/v2/50518e10/stockAdicional5");
        json = webResource.get(String.class);
        return gson.fromJson(json, new TypeToken<ProductoStock>() {
        }.getType());
    }

}
