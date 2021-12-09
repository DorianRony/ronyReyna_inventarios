package com.prueba.ronyreyna_inventarios.repository;

import com.prueba.ronyreyna_inventarios.models.entity.Producto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface ProductoRepository extends CrudRepository<Producto, Integer> {

    Producto findByCod(String cod);

    @Query("select p.price from Producto p where p.cod = ?1")
    BigDecimal costoProdPorCod(String codProd);
}
