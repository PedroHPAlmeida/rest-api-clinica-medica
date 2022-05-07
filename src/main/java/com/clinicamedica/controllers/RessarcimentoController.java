package com.clinicamedica.controllers;

import com.clinicamedica.entities.Ressarcimento;
import com.clinicamedica.services.RessarcimentoService;
import com.clinicamedica.views.RessarcimentoView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/ressarcimentos")
public class RessarcimentoController {

    @Autowired
    private RessarcimentoService ressarcimentoService;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public Ressarcimento salvarRessarcimento(@RequestBody RessarcimentoView ressarcimentoView){
        return ressarcimentoService.salvarRessarcimento(ressarcimentoView);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Ressarcimento> listarRessarcimentos(){
        return ressarcimentoService.listarRessarcimentos();
    }

    @GetMapping(path = "/buscar-por-id-pagamento/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Ressarcimento buscarRessarcimentoPorIdPagamento(@PathVariable Long id){
        return ressarcimentoService.buscarRessarcimentoPorIdPagamento(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ressarcimento não encontrado."));
    }
}
