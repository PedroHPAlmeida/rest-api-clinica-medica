package com.clinicamedica.services;

import com.clinicamedica.entities.Especialidade;
import com.clinicamedica.entities.Servico;
import com.clinicamedica.repositories.IServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ServicoService {

    @Autowired
    private IServicoRepository servicoRepository;

    @Autowired
    private EspecialidadeService especialidadeService;

    public Servico salvarServico(Servico servico){
        if(servico.getEspecialidade().getIdEspecialidade() == null){
            especialidadeService.salvarEspecialidade(servico.getEspecialidade());
        }
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

    public List<Servico> listarServicoPorIdEspecialidade(Long idEspecialidade){
        if(idEspecialidade != null){
            Especialidade especialidade = especialidadeService.buscarEspecialidadePorId(idEspecialidade)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Especialidade não encontrada."));
            return servicoRepository.findByEspecialidade(especialidade);
        }
        return this.listarServicos();
    }
}
