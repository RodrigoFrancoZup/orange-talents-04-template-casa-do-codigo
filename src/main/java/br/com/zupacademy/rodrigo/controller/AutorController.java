package br.com.zupacademy.rodrigo.controller;

import br.com.zupacademy.rodrigo.modelo.Autor;
import br.com.zupacademy.rodrigo.modelo.dto.AutorDto;
import br.com.zupacademy.rodrigo.modelo.form.AutorForm;
import br.com.zupacademy.rodrigo.repository.AutorRepository;
import br.com.zupacademy.rodrigo.validator.ProibeEmailDuplicadoAutorValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;


@RestController
@RequestMapping("/autor")
public class AutorController {

    @Autowired
    private AutorRepository autorRepository;

    @Autowired
    private ProibeEmailDuplicadoAutorValidator proibeEmailDuplicadoAutorValidator;

    @InitBinder
    public void init(WebDataBinder binder){
        binder.addValidators(proibeEmailDuplicadoAutorValidator);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<AutorDto> cadastro(@RequestBody @Valid AutorForm form){
        Autor autor = form.convertAutorFormParaAutor();
        autorRepository.save(autor);
        return ResponseEntity.ok(new AutorDto(autor));
    }
}
