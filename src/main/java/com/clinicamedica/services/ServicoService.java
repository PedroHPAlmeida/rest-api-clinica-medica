package com.clinicamedica.services;

import com.clinicamedica.entities.Servico;
import com.clinicamedica.repositories.IServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicoService {

    @Autowired
    private IServicoRepository servicoRepository;

    @Autowired
    private EspecialidadeService especialidadeService;

    public Servico salvarServico(Servico servico){
        especialidadeService.salvarEspecialidade(servico.getEspecialidade());
        return servicoRepository.save(servico);
    }

    public List<Servico> listarServicos(){
        return servicoRepository.findAll();
    }

    public Optional<Servico> buscarServicoPorId(Long id){
        return servicoRepository.findById(id);
    }

    public void deletarServicoPorId(Long id){
        servicoRepository.deleteById(id);
    }
}
