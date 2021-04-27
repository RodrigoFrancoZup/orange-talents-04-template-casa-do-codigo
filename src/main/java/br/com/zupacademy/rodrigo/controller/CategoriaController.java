package br.com.zupacademy.rodrigo.controller;

import br.com.zupacademy.rodrigo.modelo.Categoria;
import br.com.zupacademy.rodrigo.modelo.form.CategoriaForm;
import br.com.zupacademy.rodrigo.repository.CategoriaRepository;
import br.com.zupacademy.rodrigo.validator.ProibeNomeDuplicadoCategoriaValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private ProibeNomeDuplicadoCategoriaValidator proibeNomeDuplicadoCategoriaValidator;


    @InitBinder
    public void init(WebDataBinder binder){
        binder.addValidators(proibeNomeDuplicadoCategoriaValidator);
    }

    @PostMapping
    @Transactional
        public ResponseEntity<Categoria> cadastro(@RequestBody @Valid CategoriaForm form){
        Categoria categoria = form.convertCategoriaFormParaCategoria();
        categoriaRepository.save(categoria);
        return ResponseEntity.ok(categoria);
    }
}
