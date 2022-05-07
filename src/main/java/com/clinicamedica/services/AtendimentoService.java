package com.clinicamedica.services;

import com.clinicamedica.entities.Atendimento;
import com.clinicamedica.repositories.IAtendimentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AtendimentoService {

    @Autowired
    private IAtendimentoRepository atendimentoRepository;

    @Autowired
    private AgendamentoService agendamentoService;

    public Atendimento salvarAtendimento(Atendimento atendimento){
        agendamentoService.salvarAgendamento(atendimento.getAgendamento());
        return atendimentoRepository.save(atendimento);
    }

    public List<Atendimento> listarAtendimentos(){
        return atendimentoRepository.findAll();
    }
}
