package br.com.zupacademy.rodrigo.estado;

import br.com.zupacademy.rodrigo.pais.Pais;
import br.com.zupacademy.rodrigo.pais.PaisRepository;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


public class EstadoRequest {

    @NotBlank
    private String nome;

    @NotNull
    private Long idPais;

    public EstadoRequest(String nome, Long idPais) {
        this.nome = nome;
        this.idPais = idPais;
    }

    public Estado converteEstadoRequestParaEstado(PaisRepository paisRepository){
        Pais pais = paisRepository.findById(this.idPais).get();
        return new Estado(this.nome, pais);
    }

    public String getNome() {
        return nome;
    }

    public Long getIdPais() {
        return idPais;
    }
}
