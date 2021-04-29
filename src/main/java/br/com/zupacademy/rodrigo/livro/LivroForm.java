package br.com.zupacademy.rodrigo.livro;

import br.com.zupacademy.rodrigo.annotations.ExistsId;
import br.com.zupacademy.rodrigo.annotations.UniqueValue;
import br.com.zupacademy.rodrigo.autor.Autor;
import br.com.zupacademy.rodrigo.categoria.Categoria;
import br.com.zupacademy.rodrigo.livro.Livro;
import br.com.zupacademy.rodrigo.autor.AutorRepository;
import br.com.zupacademy.rodrigo.categoria.CategoriaRepository;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

public class LivroForm {

    @NotBlank
    @UniqueValue(domainClass = Livro.class, fieldName = "titulo")
    private String titulo;

    @NotBlank
    @Length(max = 500)
    private String resumo;
    private String sumario;

    @DecimalMin("20.00")
    private BigDecimal preco;

    @Min(100)
    private Long numeroDePaginas;

    @NotBlank
    @UniqueValue(domainClass = Livro.class, fieldName = "isbn")
    private String isbn;

    @Future
    @JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING)
    private LocalDate dataDePublicacao;

    @NotNull
    private Long categoriaId;

    @NotNull
    @ExistsId(domainClass = Autor.class, fieldName = "id")
    private Long autorId;

    public LivroForm(String titulo, String resumo, String sumario, BigDecimal preco, Long numeroDePaginas, String isbn, Long categoriaId, Long autorId) {
        this.titulo = titulo;
        this.resumo = resumo;
        this.sumario = sumario;
        this.preco = preco;
        this.numeroDePaginas = numeroDePaginas;
        this.isbn = isbn;
        this.categoriaId = categoriaId;
        this.autorId = autorId;
    }

    public Livro convertLivroFormParaLivro(AutorRepository autorRepository, CategoriaRepository categoriaRepository) {
        Optional<Autor> autor = autorRepository.findById(this.autorId);
        Optional<Categoria> categoria = categoriaRepository.findById(this.categoriaId);
        Livro livro = new Livro(this.titulo, this.resumo, this.sumario ,this.preco, this.numeroDePaginas,
                this.isbn, this.dataDePublicacao,autor.get(), categoria.get());

        return livro;
    }

    /*
     * Esse método é utilizado na validação que eu fazia
     * para ver se data é passada.
     * Atualmente usamos o @Future para isso.
     */
    public LocalDate getDataDePublicacao() {
        return dataDePublicacao;
    }

    /*
     * LocalDate não funcionou dentro do construtor.
     */
    public void setDataDePublicacao(LocalDate dataDePublicacao) {
        this.dataDePublicacao = dataDePublicacao;
    }
}
