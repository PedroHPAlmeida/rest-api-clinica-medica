package com.clinicamedica.services;

import com.clinicamedica.entities.NotaFiscal;
import com.clinicamedica.entities.Paciente;
import com.clinicamedica.entities.Pagamento;
import com.clinicamedica.entities.Ressarcimento;
import com.clinicamedica.repositories.INotaFiscalRepository;
import com.clinicamedica.repositories.IPagamentoRepository;
import com.clinicamedica.repositories.IRessarcimentoRepository;
import org.aspectj.weaver.ast.Not;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class NotaFiscalService {

    @Autowired
    private INotaFiscalRepository notaFiscalRepository;

    @Autowired
    private IPagamentoRepository pagamentoRepository;

    @Autowired
    private IRessarcimentoRepository ressarcimentoRepository;

    public NotaFiscal salvarNotaFiscal(NotaFiscal notaFiscal){
        return notaFiscalRepository.save(notaFiscal);
    }

    public List<NotaFiscal> listarNotasFiscais(){
        return notaFiscalRepository.findAll();
    }

    public Optional<NotaFiscal> buscarNotaFiscalPorId(Long id){
        return notaFiscalRepository.findById(id);
    }

    public Paciente buscarPacientePorIdNotaFiscal(Long idNotaFiscal){
        NotaFiscal notaFiscal = this.buscarNotaFiscalPorId(idNotaFiscal)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Nota fiscal não encontrada."));

        Optional<Pagamento> pagamento = pagamentoRepository.findByNotaFiscal(notaFiscal);
        if(pagamento.isPresent()){
            return pagamento.get().getAgendamento().getPaciente();
        } else {
            Optional<Ressarcimento> ressarcimento = ressarcimentoRepository.findByNotaFiscal(notaFiscal);
            return ressarcimento.get().getPagamento().getAgendamento().getPaciente();
        }
    }
}
