package br.com.zupacademy.rodrigo.estado;

import br.com.zupacademy.rodrigo.pais.Pais;

import javax.persistence.*;

@Entity
public class Estado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;

    @ManyToOne
    private Pais pais;

    @Deprecated
    public Estado() {
    }

    public Estado(String nome, Pais pais) {
        this.nome = nome;
        this.pais = pais;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Pais getPais() {
        return pais;
    }

    public void verificaSeuPais(Pais pais) {
        if(!this.pais.equals(pais)){
            throw  new IllegalArgumentException();
        }
    }
}
