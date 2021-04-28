package br.com.zupacademy.rodrigo.autor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;


@RestController
@RequestMapping("/autor")
public class AutorController {

    @Autowired
    private AutorRepository autorRepository;

    /*
    - Validação agora será por uma anotação genérica
      que vamos criar!

    @Autowired
    private ProibeEmailDuplicadoAutorValidator proibeEmailDuplicadoAutorValidator;

    @InitBinder
    public void init(WebDataBinder binder){
        binder.addValidators(proibeEmailDuplicadoAutorValidator);
    }
     */

    @PostMapping
    @Transactional
    public ResponseEntity<AutorDto> cadastro(@RequestBody @Valid AutorForm form){
        Autor autor = form.convertAutorFormParaAutor();
        autorRepository.save(autor);
        return ResponseEntity.ok(new AutorDto(autor));
    }
}
