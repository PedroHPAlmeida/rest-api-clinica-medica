package com.clinicamedica.repositories;

import com.clinicamedica.entities.NotaFiscal;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class NotaFiscalCustomRepository {

    private EntityManager entityManager;

    public NotaFiscalCustomRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<NotaFiscal> listarNotasPorPeriodo(String data){
        String query = "SELECT * FROM notas_fiscais as nf " +
                       "WHERE nf.data_emissao >= :data";

        var q = entityManager.createNativeQuery(query, NotaFiscal.class);
        q.setParameter("data", data);

        return q.getResultList();
    }
}
