package br.com.zupacademy.rodrigo.cliente;

import br.com.zupacademy.rodrigo.estado.Estado;
import br.com.zupacademy.rodrigo.estado.EstadoResponse;
import br.com.zupacademy.rodrigo.pais.Pais;
import br.com.zupacademy.rodrigo.pais.PaisResponse;

public class ClienteResponse {

    private Long id;
    private String email;
    private String nome;
    private String sobrenome;
    private String documento;
    private String endereco;
    private String complemento;
    private String cidade;
    private PaisResponse pais;
    private EstadoResponse estado;
    private String telefone;
    private String cep;

    public ClienteResponse(Cliente cliente) {
        this.id = cliente.getId();
        this.email = cliente.getEmail();
        this.nome = cliente.getNome();
        this.sobrenome = cliente.getSobrenome();
        this.documento = cliente.getDocumento();
        this.endereco = cliente.getEndereco();
        this.complemento = cliente.getComplemento();
        this.cidade = cliente.getCidade();
        this.pais = new PaisResponse(cliente.getPais());
        this.estado = null;
        if(cliente.getEstado() != null){
            this.estado = new EstadoResponse(cliente.getEstado());
        }
        this.telefone = cliente.getTelefone();
        this.cep = cliente.getCep();
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getNome() {
        return nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public String getDocumento() {
        return documento;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getComplemento() {
        return complemento;
    }

    public String getCidade() {
        return cidade;
    }

    public PaisResponse getPais() {
        return pais;
    }

    public EstadoResponse getEstado() {
        return estado;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getCep() {
        return cep;
    }
}
