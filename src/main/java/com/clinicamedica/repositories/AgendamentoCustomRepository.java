package com.clinicamedica.repositories;

import com.clinicamedica.entities.Agendamento;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.time.LocalDate;
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

    public List<Agendamento> listarAgendamentosPorIdMedicoEPeriodo(Long idMedico, Integer dias, Integer status){
        String query = "SELECT agend.* FROM agendamentos AS agend " +
                       "INNER JOIN medicos as med on agend.medico_id_funcionario = med.id_funcionario " +
                       "WHERE med.id_funcionario = :idMedico ";
        if(dias != null) {
            if (dias < 0) {
                query += "AND agend.data_agendada >= :dataAnterior AND agend.data_agendada < :hoje";
            } else {
                query += "AND agend.data_agendada >= :hoje AND agend.data_agendada <= :dataFutura";
            }
        }

        if(status != null){
            query += " AND agend.status = :status";
        }

        var q = entityManager.createNativeQuery(query, Agendamento.class);
        q.setParameter("idMedico", idMedico);

        if(dias != null) {
            q.setParameter("hoje", LocalDate.now().toString());
            if (dias < 0) {
                String dataAnterior = LocalDate.now().minusDays(-dias).toString();
                q.setParameter("dataAnterior", dataAnterior);
            } else {
                String dataFutura = LocalDate.now().plusDays(dias).toString();
                System.out.println(dataFutura);
                q.setParameter("dataFutura", dataFutura);
            }
        }

        if(status != null){
            q.setParameter("status", status);
        }

        return q.getResultList();
    }
}
