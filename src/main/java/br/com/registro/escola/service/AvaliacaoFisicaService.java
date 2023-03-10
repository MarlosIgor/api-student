package br.com.registro.escola.service;

import br.com.registro.escola.exception.NotFoundException;
import br.com.registro.escola.model.Aluno;
import br.com.registro.escola.model.AvaliacaoFisica;
import br.com.registro.escola.model.form.AvaliacaoFisicaForm;
import br.com.registro.escola.model.form.AvaliacaoFisicaUpdateForm;
import br.com.registro.escola.repository.AlunoRepository;
import br.com.registro.escola.repository.AvaliacaoFisicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AvaliacaoFisicaService {

    @Autowired
    private AvaliacaoFisicaRepository avaliacaoFisicaRepository;

    @Autowired
    private AlunoRepository alunoRepository;

    public AvaliacaoFisica createAvaliacaoFisica(AvaliacaoFisicaForm avaliacaoFisicaForm) {
        Long alunoId = avaliacaoFisicaForm.getAlunoId();

        Aluno aluno = alunoRepository.findById(alunoId)
                .orElseThrow(() -> new NotFoundException("Id não encontrado : " + alunoId));

        AvaliacaoFisica avaliacaoFisica = new AvaliacaoFisica();
        avaliacaoFisica.setAluno(aluno);
        avaliacaoFisica.setPeso(avaliacaoFisicaForm.getPeso());
        avaliacaoFisica.setAltura(avaliacaoFisicaForm.getAltura());

        return avaliacaoFisicaRepository.save(avaliacaoFisica);
    }

    public List<AvaliacaoFisica> getAllAvaliacaoFisica() {
        return avaliacaoFisicaRepository.findAll();
    }

    public AvaliacaoFisica getByIdAvaliacaoFisica(Long id) {
        return avaliacaoFisicaRepository.findById(id).orElseThrow(() -> new NotFoundException("Id não encontrado : " + id));
    }

    public AvaliacaoFisica updateByIdAvaliacaoFisica(AvaliacaoFisicaUpdateForm avaliacaoFisicaUpdateForm, Long id) {
        AvaliacaoFisica avaliacaoFisica = avaliacaoFisicaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Id não encontrado : " + id));

        avaliacaoFisica.setAltura(avaliacaoFisicaUpdateForm.getAltura());
        avaliacaoFisica.setPeso(avaliacaoFisicaUpdateForm.getPeso());

        return avaliacaoFisicaRepository.save(avaliacaoFisica);
    }

    public void deleteByIdAvaliacaoFisica(Long id) {
        avaliacaoFisicaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Id não encontrado : " + id));

        avaliacaoFisicaRepository.deleteById(id);
    }

}
