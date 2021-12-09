package com.prueba.ronyreyna_inventarios.repository;

import com.prueba.ronyreyna_inventarios.models.entity.Cliente;
import com.prueba.ronyreyna_inventarios.models.entity.Tienda_Transacciones;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface TiendaTransaccionRepository extends CrudRepository<Tienda_Transacciones, Integer> {

    @Query("select t from Tienda_Transacciones t where t.cliente = ?1 and (t.fecha = ?2 or t.fecha = ?3 or t.fecha between ?2 and ?3)")
    List<Tienda_Transacciones> transaccionesClienteFechas(Cliente cliente, Date inicio, Date fin);

}
