package br.com.zupacademy.rodrigo.modelo.form;

import br.com.zupacademy.rodrigo.annotations.UniqueValue;
import br.com.zupacademy.rodrigo.modelo.Autor;
import br.com.zupacademy.rodrigo.modelo.Categoria;
import br.com.zupacademy.rodrigo.modelo.Livro;
import br.com.zupacademy.rodrigo.repository.AutorRepository;
import br.com.zupacademy.rodrigo.repository.CategoriaRepository;
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

    @JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING)
    private LocalDate dataDePublicacao;

    @NotNull
    private Long categoriaId;

    @NotNull
    private Long autorId;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getResumo() {
        return resumo;
    }

    public void setResumo(String resumo) {
        this.resumo = resumo;
    }

    public String getSumario() {
        return sumario;
    }

    public void setSumario(String sumario) {
        this.sumario = sumario;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public Long getNumeroDePaginas() {
        return numeroDePaginas;
    }

    public void setNumeroDePaginas(Long numeroDePaginas) {
        this.numeroDePaginas = numeroDePaginas;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public LocalDate getDataDePublicacao() {
        return dataDePublicacao;
    }

    public void setDataDePublicacao(LocalDate dataDePublicacao) {
        this.dataDePublicacao = dataDePublicacao;
    }

    public Long getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(Long categoriaId) {
        this.categoriaId = categoriaId;
    }

    public Long getAutorId() {
        return autorId;
    }

    public void setAutorId(Long autorId) {
        this.autorId = autorId;
    }

    public Livro convertLivroFormParaLivro(AutorRepository autorRepository, CategoriaRepository categoriaRepository) {
        Optional<Autor> autor = autorRepository.findById(this.autorId);
        Optional<Categoria> categoria = categoriaRepository.findById(this.categoriaId);
        Livro livro = new Livro(this.titulo, this.resumo, this.preco, this.numeroDePaginas,
                this.isbn, autor.get(), categoria.get());
        livro.setSumario(this.getSumario());
        livro.setDataDePublicacao(this.getDataDePublicacao());
        return livro;
    }
}
