package br.com.zupacademy.rodrigo.livro;

import br.com.zupacademy.rodrigo.autor.AutorRepository;
import br.com.zupacademy.rodrigo.categoria.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/livro")
public class LivroController {

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private AutorRepository autorRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    /*

    Vamos substituir essa validação pela annotation @Future
    no atributo dataDePubiclacao da classe LivroForm

    @Autowired
    private ProibeDataDePublicacaoDeLivroSerPassadaValidator proibeDataDePublicacaoDeLivroSerPassadaValidator;

    @InitBinder
    public void init(WebDataBinder binder){
        binder.addValidators(proibeDataDePublicacaoDeLivroSerPassadaValidator);
    }
     */

    @PostMapping
    @Transactional
    public ResponseEntity<LivroDto> cadastro(@RequestBody @Valid LivroForm form){
        Livro livro = form.convertLivroFormParaLivro(autorRepository, categoriaRepository);
        livroRepository.save(livro);
        return ResponseEntity.ok(new LivroDto(livro));
    }

    @GetMapping
    public ResponseEntity<List<LivroDto>> lista(){
        List<Livro> livros = livroRepository.findAll();
        List<LivroDto> livrosDto = LivroDto.convertListaDeLivroParaListaDeLivroDto(livros);
        return ResponseEntity.ok(livrosDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> detalhe(@PathVariable Long id){
        Optional<Livro> livro = livroRepository.findById(id);
        if(livro.isPresent()){
            return ResponseEntity.ok(new LivroDetalheResponse(livro.get()));
        }else{
            return ResponseEntity.badRequest().build();
        }
    }
}
