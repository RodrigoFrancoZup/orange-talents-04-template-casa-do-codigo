package br.com.zupacademy.rodrigo.controller;

import br.com.zupacademy.rodrigo.modelo.Livro;
import br.com.zupacademy.rodrigo.modelo.dto.LivroDto;
import br.com.zupacademy.rodrigo.modelo.form.LivroForm;
import br.com.zupacademy.rodrigo.repository.AutorRepository;
import br.com.zupacademy.rodrigo.repository.CategoriaRepository;
import br.com.zupacademy.rodrigo.repository.LivroRepository;
import br.com.zupacademy.rodrigo.validator.ProibeDataDePublicacaoDeLivroSerPassadaValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/livro")
public class LivroController {

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private AutorRepository autorRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private ProibeDataDePublicacaoDeLivroSerPassadaValidator proibeDataDePublicacaoDeLivroSerPassadaValidator;

    @InitBinder
    public void init(WebDataBinder binder){
        binder.addValidators(proibeDataDePublicacaoDeLivroSerPassadaValidator);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<LivroDto> cadastro(@RequestBody @Valid LivroForm form){
        Livro livro = form.convertLivroFormParaLivro(autorRepository, categoriaRepository);
        livroRepository.save(livro);
        return ResponseEntity.ok(new LivroDto(livro));
    }
}
