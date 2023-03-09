package br.com.registro.escola.service;

import br.com.registro.escola.exception.NotFoundException;
import br.com.registro.escola.model.Aluno;
import br.com.registro.escola.model.form.AlunoForm;
import br.com.registro.escola.model.form.AlunoUpdateForm;
import br.com.registro.escola.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;

    public Aluno createAluno(AlunoForm alunoForm) {
        Aluno aluno = new Aluno();

        if (alunoRepository.existsByCpf(alunoForm.getCpf())) {
            throw new NotFoundException("Já existe um aluno com esse CPF: " + alunoForm.getCpf());
        }

        aluno.setNome(alunoForm.getNome());
        aluno.setCpf(alunoForm.getCpf());
        aluno.setBairro(alunoForm.getBairro());
        aluno.setDataDeNascimento(alunoForm.getDataDeNascimento());

        return alunoRepository.save(aluno);
    }

    public List<Aluno> getAllAluno() {
        return alunoRepository.findAll();
    }

    public Aluno getByIdAluno(Long id) {
        return alunoRepository.findById(id).orElseThrow(() -> new NotFoundException("Id não encontrado : " + id));
    }

    public Aluno updateByIdAluno(AlunoUpdateForm alunoUpdateForm, Long id) {
        Aluno alunoExiste = alunoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Id não encontrado : " + id));

        alunoExiste.setNome(alunoUpdateForm.getNome());
        alunoExiste.setBairro(alunoUpdateForm.getBairro());
        alunoExiste.setDataDeNascimento(alunoUpdateForm.getDataDeNascimento());

        return alunoRepository.save(alunoExiste);
    }

    public void deleteByIdAluno(Long id) {
        alunoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Id não encontrado : " + id));

        alunoRepository.deleteById(id);
    }

}
