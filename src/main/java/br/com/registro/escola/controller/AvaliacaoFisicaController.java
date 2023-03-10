package br.com.registro.escola.controller;

import br.com.registro.escola.model.AvaliacaoFisica;
import br.com.registro.escola.model.form.AvaliacaoFisicaForm;
import br.com.registro.escola.model.form.AvaliacaoFisicaUpdateForm;
import br.com.registro.escola.service.AvaliacaoFisicaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/avaliacoes")
public class AvaliacaoFisicaController {

    @Autowired
    private AvaliacaoFisicaService avaliacaoFisicaService;


    @PostMapping("/criar")
    public ResponseEntity<AvaliacaoFisica> save(@Valid @RequestBody AvaliacaoFisicaForm avaliacaoFisicaForm) {
        return new ResponseEntity<>(avaliacaoFisicaService.createAvaliacaoFisica(avaliacaoFisicaForm), HttpStatus.CREATED);
    }

    @GetMapping("/listar-todos")
    public ResponseEntity<List<AvaliacaoFisica>> getAll() {
        return new ResponseEntity<>(avaliacaoFisicaService.getAllAvaliacaoFisica(), HttpStatus.OK);
    }

    @GetMapping("/listar-id/{id}")
    public ResponseEntity<AvaliacaoFisica> getById(@Valid @PathVariable("id") Long id) {
        return new ResponseEntity<>(avaliacaoFisicaService.getByIdAvaliacaoFisica(id), HttpStatus.OK);
    }

    @PutMapping("/atualizar-id/{id}")
    public ResponseEntity<AvaliacaoFisica> update(@Valid @RequestBody AvaliacaoFisicaUpdateForm avaliacaoFisicaUpdateForm, @PathVariable Long id) {
        return new ResponseEntity<>(avaliacaoFisicaService.updateByIdAvaliacaoFisica(avaliacaoFisicaUpdateForm, id), HttpStatus.OK);
    }

    @DeleteMapping("/deletar-id/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@Valid @PathVariable("id") Long id) {
        avaliacaoFisicaService.deleteByIdAvaliacaoFisica(id);
    }

}
