package br.com.zupacademy.rodrigo.pais;

import br.com.zupacademy.rodrigo.annotations.UniqueValue;
import javax.validation.constraints.NotBlank;

public class PaisRequest {

    @NotBlank
    @UniqueValue(domainClass = Pais.class, fieldName = "nome")
    private String nome;

    public PaisRequest() {
    }

    public PaisRequest(String nome) {
        this.nome = nome;
    }

    public Pais convertePaisRequestParaPais(){
        return new Pais(this.nome);
    }

    public String getNome() {
        return nome;
    }


}
