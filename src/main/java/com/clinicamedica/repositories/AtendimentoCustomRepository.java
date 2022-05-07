package com.clinicamedica.repositories;

import com.clinicamedica.entities.Atendimento;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class AtendimentoCustomRepository {

    private final EntityManager entityManager;

    public AtendimentoCustomRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Atendimento> listarAtendimentosPorCpfPaciente(String cpf){
        String query = "select atendimentos.id_atendimento, atendimentos.data, atendimentos.diagnostico, atendimentos.observacoes, atendimentos.agendamento_id_agendamento from atendimentos";
        query += " inner join agendamentos on atendimentos.agendamento_id_agendamento = agendamentos.id_agendamento";
        query += " inner join pacientes on agendamentos.paciente_cpf = pacientes.cpf";
        query += " where pacientes.cpf = :cpf";

        var q = entityManager.createNativeQuery(query, Atendimento.class);
        q.setParameter("cpf", cpf);

        return q.getResultList();
    }
}
