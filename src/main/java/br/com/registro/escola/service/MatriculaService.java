package br.com.registro.escola.service;

import br.com.registro.escola.exception.NotFoundException;
import br.com.registro.escola.model.Aluno;
import br.com.registro.escola.model.Matricula;
import br.com.registro.escola.model.form.MatriculaForm;
import br.com.registro.escola.repository.AlunoRepository;
import br.com.registro.escola.repository.MatriculaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatriculaService {

    @Autowired
    private MatriculaRepository matriculaRepository;

    @Autowired
    private AlunoRepository alunoRepository;

    public Matricula createAvaliacaoFisica(MatriculaForm matriculaForm, Long id) {
        Matricula matricula = new Matricula();

        Aluno aluno = alunoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Id não encontrado : " + id));

        if (matriculaRepository.existsByAlunoId(matriculaForm.getAlunoId())) {
            throw new NotFoundException("Esse aluno com ID: " + id + " já está matriculado");
        }

        matricula.setAluno(alunoRepository.getReferenceById(aluno.getId()));

        return matriculaRepository.save(matricula);
    }

    public List<Matricula> getAllAvaliacaoFisica() {
        return matriculaRepository.findAll();
    }

    public Matricula getByIdAvaliacaoFisica(Long id) {
        return matriculaRepository.findById(id).orElseThrow(() -> new NotFoundException("Id não encontrado : " + id));
    }

    public void deleteByIdMatricula(Long id) {
        matriculaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Id não encontrado : " + id));

        matriculaRepository.deleteById(id);
    }

}
