package br.com.zupacademy.rodrigo.cliente;

import br.com.zupacademy.rodrigo.annotations.UniqueValue;
import br.com.zupacademy.rodrigo.estado.Estado;
import br.com.zupacademy.rodrigo.estado.EstadoRepository;
import br.com.zupacademy.rodrigo.pais.Pais;
import br.com.zupacademy.rodrigo.pais.PaisRepository;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ClienteRequest {

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String nome;

    @NotBlank
    private String sobrenome;

    @NotBlank
    @Length(min = 11,max = 14)
    @UniqueValue(domainClass = Cliente.class, fieldName = "documento")
    private String documento;

    @NotBlank
    private String endereco;

    @NotBlank
    private String complemento;

    @NotBlank
    private String cidade;

    @NotNull
    private Long idPais;
    private Long idEstado;

    @NotBlank
    private String telefone;

    @NotBlank
    private String cep;

    public ClienteRequest(String email, String nome, String sobrenome, String documento, String endereco, String complemento, String cidade, Long idPais, Long idEstado, String telefone, String cep) {
        this.email = email;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.documento = documento;
        this.endereco = endereco;
        this.complemento = complemento;
        this.cidade = cidade;
        this.idPais = idPais;
        this.idEstado = idEstado;
        this.telefone = telefone;
        this.cep = cep;
    }

    public Long getIdPais() {
        return idPais;
    }

    public Long getIdEstado() {
        return idEstado;
    }

    public Cliente converteClienteRequestParaCliente(EstadoRepository estadoRepository, PaisRepository paisRepository){
        Pais pais = paisRepository.findById(this.idPais).get();
        Cliente cliente = new Cliente(this.email, this.nome, this.sobrenome, this.documento, this.endereco, this.complemento, this.cidade, pais, this.telefone, this.cep);
        if(this.idEstado != null){
            Estado estado = estadoRepository.findById(this.idEstado).get();
            estado.verificaSeuPais(pais);
            cliente.setEstado(estado);
        }
        return cliente;
    }
}
