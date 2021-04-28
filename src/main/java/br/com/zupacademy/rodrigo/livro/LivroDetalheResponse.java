package br.com.zupacademy.rodrigo.livro;

import br.com.zupacademy.rodrigo.autor.AutorDetalheResponse;

import java.math.BigDecimal;
import java.time.LocalDate;

public class LivroDetalheResponse {

    private Long id;
    private String titulo;
    private String resumo;
    private String sumario;
    private BigDecimal preco;
    private Long numeroDePaginas;
    private String isbn;
    private LocalDate dataDePublicacao;
    private String categoria;
    private AutorDetalheResponse autor;
    private String descricaoAutor;

    public LivroDetalheResponse(Livro livro) {
        this.id = livro.getId();
        this.titulo = livro.getTitulo();
        this.resumo = livro.getResumo();
        this.sumario = livro.getSumario();
        this.preco = livro.getPreco();
        this.numeroDePaginas = livro.getNumeroDePaginas();
        this.isbn = livro.getIsbn();
        this.dataDePublicacao = livro.getDataDePublicacao();
        this.categoria = livro.getCategoria().getNome();
        this.autor = new AutorDetalheResponse(livro.getAutor());
        this.descricaoAutor = livro.getAutor().getDescricao();
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getResumo() {
        return resumo;
    }

    public String getSumario() {
        return sumario;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public Long getNumeroDePaginas() {
        return numeroDePaginas;
    }

    public String getIsbn() {
        return isbn;
    }

    public LocalDate getDataDePublicacao() {
        return dataDePublicacao;
    }

    public String getCategoria() {
        return categoria;
    }

    public AutorDetalheResponse getAutor() {
        return autor;
    }


    public String getDescricaoAutor() {
        return descricaoAutor;
    }
}
