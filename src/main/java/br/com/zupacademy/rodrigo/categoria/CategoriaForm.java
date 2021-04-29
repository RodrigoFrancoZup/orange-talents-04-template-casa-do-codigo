package br.com.zupacademy.rodrigo.categoria;

import br.com.zupacademy.rodrigo.annotations.UniqueValue;
import br.com.zupacademy.rodrigo.categoria.Categoria;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

public class CategoriaForm {

    @NotBlank
    @UniqueValue(domainClass = Categoria.class, fieldName = "nome")
    private String nome;

    public CategoriaForm(String nome) {
        this.nome = nome;
    }

    @Deprecated
    public CategoriaForm(){

    }


    public Categoria convertCategoriaFormParaCategoria(){
        return new Categoria(nome);
    }

    /*
     * Esse método é utilizado na validação
     * que verifica se nome de categoria já existe.
     */
    public String getNome() {
        return nome;
    }
}
