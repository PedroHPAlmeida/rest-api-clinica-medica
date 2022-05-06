package com.clinicamedica.controllers;

import com.clinicamedica.entities.Pagamento;
import com.clinicamedica.services.PagamentoService;
import com.clinicamedica.views.PagamentoView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping(path = "/api/pagamentos")
public class PagamentoController {

    @Autowired
    private PagamentoService pagamentoService;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public Pagamento salvarPagamento(@RequestBody PagamentoView pagamento){
        return pagamentoService.salvarPagamento(pagamento);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Pagamento> listarPagamentos(){
        return pagamentoService.listarPagamentos();
    }

    @GetMapping(path = "/buscar-por-id-agendamento/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Pagamento buscarPagamentoPorIdAgendamento(@PathVariable Long id){
        return pagamentoService.buscarPagamentoPorIdAgendamento(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pagamento não encontrado."));
    }
}
