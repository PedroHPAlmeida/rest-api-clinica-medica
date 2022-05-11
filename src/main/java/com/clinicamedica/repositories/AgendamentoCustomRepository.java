package com.clinicamedica.repositories;

import com.clinicamedica.entities.Agendamento;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class AgendamentoCustomRepository {

    private final EntityManager entityManager;

    public AgendamentoCustomRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Agendamento> listarAgendamentosPorCpfEStatus(String cpf, Integer status){
        String query =  "select agend.id_agendamento, agend.data, agend.data_agendada, " +
                        "agend.hora_agendada, agend.status, agend.medico_id_funcionario, " +
                        "agend.paciente_cpf, agend.recepcionista_id_funcionario, agend.servico_id_servico from agendamentos as agend ";

        String condicao = " where ";

        if(cpf != null && !cpf.equals("")){
            query += "inner join pacientes on agend.paciente_cpf = pacientes.cpf ";
            query += condicao + " pacientes.cpf = :cpf ";
            condicao = "and ";
        }

        if(status != null){
           query += condicao + "agend.status = :status";
        }

        var q = entityManager.createNativeQuery(query, Agendamento.class);

        if(cpf != null && !cpf.equals("")){
           q.setParameter("cpf", cpf);
        }

        if(status != null){
            q.setParameter("status", status);
        }
        System.out.println(query);
        return q.getResultList();
    }
}
