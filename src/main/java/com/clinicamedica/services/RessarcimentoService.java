package com.clinicamedica.services;

import com.clinicamedica.entities.Clinica;
import com.clinicamedica.entities.NotaFiscal;
import com.clinicamedica.entities.Pagamento;
import com.clinicamedica.entities.Ressarcimento;
import com.clinicamedica.repositories.IRessarcimentoRepository;
import com.clinicamedica.views.RessarcimentoView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RessarcimentoService {

    @Autowired
    private IRessarcimentoRepository ressarcimentoRepository;
    @Autowired
    private PagamentoService pagamentoService;
    @Autowired
    private ClinicaService clinicaService;
    @Autowired
    private NotaFiscalService notaFiscalService;

    public Ressarcimento salvarRessarcimento(RessarcimentoView ressarcimentoView){
        Ressarcimento ressarcimento = new Ressarcimento();

        Pagamento pagamento = pagamentoService.buscarPagamentoPorId(ressarcimentoView.getIdPagamento()).get();
        ressarcimento.setPagamento(pagamento);

        Clinica clinica = clinicaService.buscarClinicaPorId(1l).get();
        NotaFiscal notaFiscal = new NotaFiscal(clinica, ressarcimentoView.getValor(), ressarcimento.getData());
        notaFiscalService.salvarNotaFiscal(notaFiscal);
        ressarcimento.setNotaFiscal(notaFiscal);

        ressarcimento.setValor(ressarcimentoView.getValor());
        ressarcimento.setFormaDeRessarcimento(ressarcimentoView.getFormaDeRessarcimento());
        ressarcimento.setMotivoRessarcimento(ressarcimentoView.getMotivoRessarcimento());
        ressarcimento.setStatus(ressarcimentoView.getStatus());

        return ressarcimentoRepository.save(ressarcimento);
    }

    public List<Ressarcimento> listarRessarcimentos(){
        return ressarcimentoRepository.findAll();
    }
}
