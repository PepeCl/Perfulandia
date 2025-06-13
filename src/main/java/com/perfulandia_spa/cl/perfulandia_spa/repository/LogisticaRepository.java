package com.perfulandia_spa.cl.perfulandia_spa.repository;

import com.perfulandia_spa.cl.perfulandia_spa.model.Logistica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LogisticaRepository extends JpaRepository<Logistica, Long> {
    List<Logistica> findAll();

    List<Logistica> findByCodigoEnvio(String codigoEnvio);
    List<Logistica> findByIdPedido(int idPedido);
    List<Logistica> findByOrigenEnvio(String origenEnvio);
    List<Logistica> findByDestinoEnvio(String destinoEnvio);
    List<Logistica> findByTransportista(String transportista);
    List<Logistica> findByFechaEnvio(java.util.Date fechaEnvio);
    List<Logistica> findByFechaLlegada(java.util.Date fechaLlegada);
    List<Logistica> findByEstadoEnvio(String estadoEnvio);


}
