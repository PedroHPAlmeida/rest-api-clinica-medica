package com.clinicamedica.controllers;

import com.clinicamedica.entities.Ressarcimento;
import com.clinicamedica.services.RessarcimentoService;
import com.clinicamedica.views.RessarcimentoView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/ressarcimentos")
public class RessarcimentoController {

    @Autowired
    private RessarcimentoService ressarcimentoService;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void salvarRessarcimento(@RequestBody RessarcimentoView ressarcimentoView){
        ressarcimentoService.salvarRessarcimento(ressarcimentoView);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Ressarcimento> listarRessarcimentos(){
        return ressarcimentoService.listarRessarcimentos();
    }
}
