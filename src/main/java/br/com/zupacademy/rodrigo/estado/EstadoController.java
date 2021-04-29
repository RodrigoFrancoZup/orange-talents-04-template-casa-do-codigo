package br.com.zupacademy.rodrigo.estado;

import br.com.zupacademy.rodrigo.pais.PaisRepository;
import br.com.zupacademy.rodrigo.validator.ProibeNomeEstadoRepetidoNoMesmoPais;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.annotation.WebInitParam;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/estado")
public class EstadoController {

    @Autowired
    private EstadoRepository estadoRepository;

    @Autowired
    private PaisRepository paisRepository;

    @Autowired
    private ProibeNomeEstadoRepetidoNoMesmoPais proibeNomeEstadoRepetidoNoMesmoPais;

    @InitBinder
    public void Init(WebDataBinder binder){
        binder.addValidators(proibeNomeEstadoRepetidoNoMesmoPais);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<EstadoResponse> cadastro(@RequestBody @Valid EstadoRequest estadoRequest){
        Estado estado = estadoRequest.converteEstadoRequestParaEstado(paisRepository);
        estadoRepository.save(estado);
        return ResponseEntity.ok(new EstadoResponse(estado));
    }
}
