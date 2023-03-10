package br.com.registro.escola.service;

import br.com.registro.escola.exception.NotFoundException;
import br.com.registro.escola.model.Aluno;
import br.com.registro.escola.model.Matricula;
import br.com.registro.escola.model.form.MatriculaForm;
import br.com.registro.escola.repository.AlunoRepository;
import br.com.registro.escola.repository.MatriculaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatriculaService {

    @Autowired
    private MatriculaRepository matriculaRepository;

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private JavaMailSender mailSender;

    public Matricula criarMatricula(MatriculaForm matriculaForm) {
        Matricula matricula = new Matricula();
        Long alunoId = matriculaForm.getAlunoId();
        String alunoEmail = matricula.getEmail();

        Aluno aluno = alunoRepository.findById(alunoId)
                .orElseThrow(() -> new NotFoundException("Aluno não encontrado com o ID: " + alunoId));

        if (matriculaRepository.existsByAlunoId(alunoId)) {
            throw new NotFoundException("O aluno com ID " + alunoId + " já está matriculado.");
        }

        matricula.setAluno(aluno);
        matricula.setEmail(aluno.getEmail());

        enviarEmailMatriculaCriada(matricula);

        return matriculaRepository.save(matricula);
    }

    private void enviarEmailMatriculaCriada(Matricula matricula) {
        String email = matricula.getEmail();
        Long id = matricula.getId();

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject(matricula.getAluno().getNome() + " seu cadastro foi recebido !");
        message.setText("Ola " + matricula.getAluno().getNome()
                + "! Seja muito bem-vindo(a) em nosso site. "
                + "Os seus dados de acesso estao logo abaixo. \n\n"
                + "=========================== \n"
                + "Nome: " + matricula.getAluno().getNome() + "\n"
                + "E-mail: " + matricula.getAluno().getEmail() + "\n"
                + "Cpf: " + matricula.getAluno().getCpf() + "\n"
                + "=========================== \n\n");
        mailSender.send(message);
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
