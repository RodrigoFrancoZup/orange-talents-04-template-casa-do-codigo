package br.com.zupacademy.rodrigo.cliente;

import br.com.zupacademy.rodrigo.estado.EstadoRepository;
import br.com.zupacademy.rodrigo.pais.PaisRepository;
import br.com.zupacademy.rodrigo.validator.ProibeEstadoVazioComPaisQueTemEstadoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EstadoRepository estadoRepository;

    @Autowired
    private PaisRepository paisRepository;

    @Autowired
    private ProibeEstadoVazioComPaisQueTemEstadoValidator proibeEstadoVazioComPaisQueTemEstadoValidator;

    @InitBinder
    public void Init(WebDataBinder binder){
        binder.addValidators(proibeEstadoVazioComPaisQueTemEstadoValidator);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<ClienteResponse> cadastro(@RequestBody @Valid ClienteRequest clienteRequest) {
        Cliente cliente = clienteRequest.converteClienteRequestParaCliente(estadoRepository, paisRepository);
        clienteRepository.save(cliente);
        return ResponseEntity.ok(new ClienteResponse(cliente));
    }
}
