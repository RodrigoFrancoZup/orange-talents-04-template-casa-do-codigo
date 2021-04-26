package br.com.zupacademy.rodrigo.controller;

import br.com.zupacademy.rodrigo.modelo.Autor;
import br.com.zupacademy.rodrigo.modelo.dto.AutorDto;
import br.com.zupacademy.rodrigo.modelo.form.AutorForm;
import br.com.zupacademy.rodrigo.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/autor")
public class AutorController {

    @Autowired
    private AutorRepository autorRepository;

    @PostMapping
    public ResponseEntity<AutorDto> cadastro(@RequestBody @Valid AutorForm form){
        Autor autor = form.convertAutorFormParaAutor();
        autorRepository.save(autor);
        return ResponseEntity.ok(new AutorDto(autor));
    }
}
