package com.clinicamedica.services;

import com.clinicamedica.entities.*;
import com.clinicamedica.repositories.AgendamentoCustomRepository;
import com.clinicamedica.repositories.IAgendamentoRepository;
import com.clinicamedica.views.AgendamentoView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
    @Autowired
    private AgendamentoCustomRepository agendamentoCustomRepository;

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

    public List<Agendamento> listarAgendamentosPorMedico(Medico medico){
        return agendamentoRepository.findByMedico(medico);
    }

    public List<Agendamento> listarAgendamentosPorCpfEStatus(String cpf, Integer status){
        return agendamentoCustomRepository.listarAgendamentosPorCpfEStatus(cpf, status);
    }

    public Optional<Agendamento> buscarAgendamentoPorId(Long id){
        return agendamentoRepository.findById(id);
    }

    public List<Agendamento> listarAgendamentoPorIdMedicoEPeriodo(Long idMedico, Integer dias, Integer status){
        if(dias == null && status == null) {
            Medico medico = medicoService.buscarMedicoPorId(idMedico)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Médico não encontrado."));
            return this.listarAgendamentosPorMedico(medico);
        }
        return agendamentoCustomRepository.listarAgendamentosPorIdMedicoEPeriodo(idMedico, dias, status);
    }
}
