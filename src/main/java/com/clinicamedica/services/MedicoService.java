package com.clinicamedica.services;

import com.clinicamedica.entities.Especialidade;
import com.clinicamedica.entities.Funcionario;
import com.clinicamedica.entities.Medico;
import com.clinicamedica.exceptions.FuncionarioException;
import com.clinicamedica.repositories.IMedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class MedicoService{
    @Autowired
    private IMedicoRepository medicoRepository;
    @Autowired
    private EspecialidadeService especialidadeService;
    @Autowired
    private FuncionarioService funcionarioService;

    public void salvarMedico(Medico medico){
        boolean emailJaExiste = funcionarioService.contarEmailsRepetidos(medico.getEmail()) > 0 ? true : false;
        if(!emailJaExiste){
            if (medico.getEspecialidade().getIdEspecialidade() == null) {
                especialidadeService.salvarEspecialidade(medico.getEspecialidade());
            }
            medicoRepository.save(medico);
        } else {
            System.err.println("Email já cadastrado no sistema, tente outro.");
            throw new FuncionarioException("Email já cadastrado no sistema, tente outro.");
        }
    }

    public void alterarMedico(Medico medicoAlterado){
        Funcionario funcionario = funcionarioService.buscarFuncionarioPorId(medicoAlterado.getIdFuncionario()).get();
        boolean emailFoiAlterado = !funcionario.getEmail().equals(medicoAlterado.getEmail());
        if(emailFoiAlterado){
            boolean emailJaExiste = funcionarioService.contarEmailsRepetidos(medicoAlterado.getEmail()) > 0 ? true : false;
            if(emailJaExiste){
                System.err.println("Email já cadastrado no sistema, tente outro.");
                throw new FuncionarioException("Email já cadastrado no sistema, tente outro.");
            } else {
                if (medicoAlterado.getEspecialidade().getIdEspecialidade() == null) {
                    especialidadeService.salvarEspecialidade(medicoAlterado.getEspecialidade());
                }
                medicoRepository.save(medicoAlterado);
            }
        } else {
            if (medicoAlterado.getEspecialidade().getIdEspecialidade() == null) {
                especialidadeService.salvarEspecialidade(medicoAlterado.getEspecialidade());
            }
            medicoRepository.save(medicoAlterado);
        }
    }

    public List<Medico> listarMedicos(){
        return medicoRepository.findAll();
    }

    public List<Medico> listarMedicosPorIdEspecialidade(Long idEspecialidade) {
        if(idEspecialidade == null){
            return this.listarMedicos();
        }
        Optional<Especialidade> especialidadeOptional = especialidadeService.buscarEspecialidadePorId(idEspecialidade);
        if(!especialidadeOptional.isEmpty()){
         return medicoRepository.findByEspecialidade(especialidadeOptional.get());
        }
        return Collections.emptyList();
    }

    public Optional<Medico> buscarMedicoPorId(Long id){
        return medicoRepository.findById(id);
    }

}
