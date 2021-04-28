package br.com.zupacademy.rodrigo.autor;

public class AutorDetalheResponse {

    private Long id;
    private String nome;
    private String descricao;

    public AutorDetalheResponse(Autor autor) {
        this.id = autor.getId();
        this.nome = autor.getNome();
        this.descricao = autor.getDescricao();
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }
}
