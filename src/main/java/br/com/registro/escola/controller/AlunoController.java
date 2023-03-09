package br.com.registro.escola.controller;

import br.com.registro.escola.model.Aluno;
import br.com.registro.escola.model.form.AlunoForm;
import br.com.registro.escola.model.form.AlunoUpdateForm;
import br.com.registro.escola.service.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    @Autowired
    private AlunoService alunoService;

    @PostMapping("/criar")
    public ResponseEntity<Aluno> save(@Valid @RequestBody AlunoForm alunoForm) {
        return new ResponseEntity<>(alunoService.createAluno(alunoForm), HttpStatus.CREATED);
    }

    @GetMapping("/listar-todos")
    public ResponseEntity<List<Aluno>> getAll() {
        return new ResponseEntity<>(alunoService.getAllAluno(), HttpStatus.OK);
    }

    @GetMapping("/listar-id/{id}")
    public ResponseEntity<Aluno> getById(@Valid @PathVariable("id") Long id) {
        return new ResponseEntity<>(alunoService.getByIdAluno(id), HttpStatus.OK);
    }

    @PutMapping("/atualizar-id/{id}")
    public ResponseEntity<Aluno> update(@Valid @RequestBody AlunoUpdateForm alunoUpdateForm, @PathVariable("id") Long id) {
        return new ResponseEntity<>(alunoService.updateByIdAluno(alunoUpdateForm, id), HttpStatus.OK);
    }

    @DeleteMapping("/deletar-id/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@Valid @PathVariable("id") Long id) {
        alunoService.deleteByIdAluno(id);
    }
}
