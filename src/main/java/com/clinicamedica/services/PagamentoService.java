package com.clinicamedica.services;

import com.clinicamedica.entities.Agendamento;
import com.clinicamedica.entities.Clinica;
import com.clinicamedica.entities.NotaFiscal;
import com.clinicamedica.entities.Pagamento;
import com.clinicamedica.repositories.IPagamentoRepository;
import com.clinicamedica.views.PagamentoView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class PagamentoService {

    @Autowired
    private IPagamentoRepository pagamentoRepository;
    @Autowired
    private ClinicaService clinicaService;
    @Autowired
    private NotaFiscalService notaFiscalService;
    @Autowired
    private AgendamentoService agendamentoService;

    public Pagamento salvarPagamento(PagamentoView pagamentoView){
        Pagamento pagamento = new Pagamento();

        Agendamento agendamento = agendamentoService.buscarAgendamentoPorId(pagamentoView.getIdAgendamento()).get();
        pagamento.setAgendamento(agendamento);
        pagamento.setFormaDePagamento(pagamentoView.getFormaDePagamento());
        pagamento.setDesconto(pagamentoView.getDesconto());
        pagamento.setStatus(pagamentoView.getStatus());
        pagamento.setValor(pagamentoView.getValor());

        Clinica clinica = clinicaService.buscarClinicaPorId(1l).get();
        NotaFiscal notaFiscal = new NotaFiscal(clinica, pagamento.getValor(), pagamento.getData(), 0D, agendamento.getServico().getDescricaoServico());
        notaFiscalService.salvarNotaFiscal(notaFiscal);

        pagamento.setNotaFiscal(notaFiscal);

        return pagamentoRepository.save(pagamento);
    }

    public Pagamento salvarPagamento(Pagamento pagamento){
        return pagamentoRepository.save(pagamento);
    }

    public List<Pagamento> listarPagamentos(){
        return pagamentoRepository.findAll();
    }

    public Optional<Pagamento> buscarPagamentoPorIdAgendamento(Long idAgendamento){
        Optional<Agendamento> agendamento = agendamentoService.buscarAgendamentoPorId(idAgendamento);
        if(agendamento.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Agendamento não encontrado.");
        }
        return pagamentoRepository.findByAgendamento(agendamento.get());
    }

    public Optional<Pagamento> buscarPagamentoPorId(Long id){
        return pagamentoRepository.findById(id);
    }

    public Optional<Pagamento> buscarPagamentoPorNotaFiscal(NotaFiscal notaFiscal){
        return pagamentoRepository.findByNotaFiscal(notaFiscal);
    }
}
