package com.clinicamedica.controllers;

import com.clinicamedica.entities.Servico;
import com.clinicamedica.services.ServicoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping(path = "/api/servicos")
public class ServicoController {
    @Autowired
    private ServicoService servicoService;
    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Servico salvarServico(@RequestBody Servico servico){
        return servicoService.salvarServico(servico);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Servico> listarServicos(){
        return servicoService.listarServicos();
    }

    @GetMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Servico buscarServicoPorId(@PathVariable Long id){
        return servicoService.buscarServicoPorId(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Serviço não encontrado."));
    }

    @PutMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void alterarServicoPorId(@PathVariable Long id, @RequestBody Servico servico){
        servicoService.buscarServicoPorId(id)
                .map(servicoBase -> {
                    modelMapper.map(servico, servicoBase);
                    servicoService.salvarServico(servicoBase);
                    return Void.TYPE;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Serviço não encontrado."));
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarServicoPorId(@PathVariable Long id){
        servicoService.buscarServicoPorId(id)
                .map(servico -> {
                    servicoService.deletarServicoPorId(id);
                    return Void.TYPE;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Serviço não encontrado."));
    }

}
