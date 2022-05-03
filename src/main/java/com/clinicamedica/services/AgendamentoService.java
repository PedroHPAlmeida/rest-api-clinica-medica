package com.clinicamedica.services;

import com.clinicamedica.entities.*;
import com.clinicamedica.repositories.IAgendamentoRepository;
import com.clinicamedica.views.AgendamentoView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class AgendamentoService {

    @Autowired
    private IAgendamentoRepository agendamentoRepository;
    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private FuncionarioService funcionarioService;
    @Autowired
    private ServicoService servicoService;
    @Autowired
    private MedicoService medicoService;

    public void salvarAgendamento(AgendamentoView agendamentoView){
        Funcionario recepcionista = funcionarioService.buscarFuncionarioPorId(agendamentoView.getRecepcionistaId()).get();
        Paciente paciente = pacienteService.buscarPacientePorCpf(agendamentoView.getPacienteCpf()).get();
        Medico medico = medicoService.buscarMedicoPorId(agendamentoView.getMedicoId()).get();
        LocalDate dataAgendada = agendamentoView.getDataAgendada();
        LocalTime horaAgendada = agendamentoView.getHoraAgendada();
        Servico servico = servicoService.buscarServicoPorId(agendamentoView.getServicoId()).get();
        int status = agendamentoView.getStatus();

        Agendamento agendamento = new Agendamento(recepcionista, paciente, medico, dataAgendada, horaAgendada, servico, status);

        agendamentoRepository.save(agendamento);
    }

    public List<Agendamento> listarAgendamentos(){
        return agendamentoRepository.findAll();
    }

}
