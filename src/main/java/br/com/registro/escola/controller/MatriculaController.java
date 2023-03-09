package br.com.registro.escola.controller;

import br.com.registro.escola.model.Matricula;
import br.com.registro.escola.model.form.MatriculaForm;
import br.com.registro.escola.service.MatriculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/matriculas")
public class MatriculaController {

    @Autowired
    private MatriculaService matriculaService;

    @PostMapping("/criar")
    public ResponseEntity<Matricula> save(@Valid @RequestBody MatriculaForm matriculaForm, Long id) {
        return new ResponseEntity<>(matriculaService.createAvaliacaoFisica(matriculaForm, matriculaForm.getAlunoId()), HttpStatus.CREATED);
    }

    @GetMapping("/listar-todos")
    public ResponseEntity<List<Matricula>> getAll() {
        return new ResponseEntity<>(matriculaService.getAllAvaliacaoFisica(), HttpStatus.OK);
    }

    @GetMapping("/listar-id/{id}")
    public ResponseEntity<Matricula> getById(@Valid @PathVariable("id") Long id) {
        return new ResponseEntity<>(matriculaService.getByIdAvaliacaoFisica(id), HttpStatus.OK);
    }

    @DeleteMapping("/deletar-id/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@Valid @PathVariable("id") Long id) {
        matriculaService.deleteByIdMatricula(id);
    }

}
