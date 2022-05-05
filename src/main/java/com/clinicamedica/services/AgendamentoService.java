package com.clinicamedica.services;

import com.clinicamedica.entities.*;
import com.clinicamedica.repositories.IAgendamentoRepository;
import com.clinicamedica.views.AgendamentoView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.spec.OAEPParameterSpec;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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

    public void salvarAgendamento(Agendamento agendamento){
        agendamentoRepository.save(agendamento);
    }
    public List<Agendamento> listarAgendamentos(){
        return agendamentoRepository.findAll();
    }

    public List<Agendamento> listarAgendamentosPorPaciente(String cpf){
        Paciente paciente = pacienteService.buscarPacientePorCpf(cpf).get();
        return agendamentoRepository.findByPaciente(paciente);
    }

    public List<Agendamento> listarAgendamentosPorStatus(Integer status){
        return agendamentoRepository.findByStatus(status);
    }
    public Set<Agendamento> listarAgendamentosPorCpfEStatus(String cpf, Integer status){
        Set<Agendamento> agendamentos = new HashSet<>();
        if(cpf != null && !cpf.equals("")){
            List<Agendamento> agendamentosPaciente = listarAgendamentosPorPaciente(cpf);
            agendamentos.addAll(agendamentosPaciente);
        }
        if(agendamentos.size() > 0 && status != null){
            agendamentos.removeIf(agendamento -> agendamento.getStatus() != status);
        } else if(status != null){
            List<Agendamento> agendamentosStatus = listarAgendamentosPorStatus(status);
            agendamentos.addAll(agendamentosStatus);
        }
        return agendamentos;
    }

    public Optional<Agendamento> buscarAgendamentoPorId(Long id){
        return agendamentoRepository.findById(id);
    }
}
